import { Component, OnInit } from '@angular/core';
import initFullCalendar = require('../../../assets/js/init/initFullCalendar.js');

import { SpecialDayService } from '../../service/SpecialDayService/specialday.service';
import { SpecialDayModel } from './SpecialDayModel';
import { SpecialDayPost } from './SpecialDayPost';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
declare var $: any;
declare var swal: any;


@Component({
	moduleId: module.id,
	selector: 'app-specialday',
	templateUrl: 'specialday.component.html',
	providers: [SpecialDayService]
})

export class SpecialDayComponent implements OnInit {

	listModel: SpecialDayModel[] = [];
	listSpecialDay: any[];
	that = this;
	constructor(private specialDayService: SpecialDayService) { }

	ngOnInit() {
		//$.getScript('../../../assets/js/init/initDataTable.js');

		this.prepareData();
	}
	async prepareData() {
		await this.loadData();
		var type = ['event-blue', 'event-azure', 'event-green', 'event-orange', 'event-red'];
		await this.listSpecialDay.forEach(element => {
			let color: number = Math.floor((Math.random() * 4) + 1);
			var model: SpecialDayModel = new SpecialDayModel();
			model.id = element.id;
			model.start = new Date(element.date);
			model.title = element.dateDescription;
			model.allDay = true;

			model.className = type[color];
			this.listModel.push(model);
		});
		await this.initFullCalendar();

	}

	async loadData() {
		await this.specialDayService.GetAllSpeicalDay().then((data) => {
			this.listSpecialDay = data;
		})

	}
	initFullCalendar() {
		var calendar = $('#fullCalendar');

		var today = new Date();
		var y = today.getFullYear();
		var m = today.getMonth();
		var d = today.getDate();

		calendar.fullCalendar({

			viewRender: (view, element) => {
				// We make sure that we activate the perfect scrollbar when the view isn't on Month
				if (view.name != 'month') {
					$(element).find('.fc-scroller').perfectScrollbar();
				}
			},
			header: {
				left: 'title',
				center: 'month,agendaWeek,agendaDay',
				right: 'prev,next,today'
			},
			defaultDate: today,
			selectable: true,
			selectHelper: true,
			views: {
				month: { // name of view
					titleFormat: 'MMMM YYYY'
					// other view-specific options here
				},
				week: {
					titleFormat: " MMMM D YYYY"
				},
				day: {
					titleFormat: 'D MMM, YYYY'
				}
			},
			dayClick: (date, jsEvent, view) => {
				swal({
					title: 'Create an Event',
					html: '<div class="form-group">' +
					'<input class="form-control" placeholder="Event Title" id="input-field">' +
					'</div>',
					showCancelButton: true,
					confirmButtonClass: 'btn btn-success',
					cancelButtonClass: 'btn btn-danger',
					buttonsStyling: false
				}).then(async (result) => {
					var eventData;
					var specialday: SpecialDayPost = new SpecialDayPost();
					var event_title = $('#input-field').val();
					if (event_title) {

						specialday.date = date;
						specialday.dateDescription = event_title;
						await this.specialDayService.AddNewSpeicalDay(specialday).toPromise().then((data) => {
							eventData = {
								id:data.id,
								title: event_title,
								start: date,
								end: date
							};
							calendar.fullCalendar('renderEvent', eventData, true); // stick? = true
						}).catch(() => {
							swal(
								'Error',
								'Some error occurred,pls try again latter !',
								'error'
							)
						})

					}
					calendar.fullCalendar('unselect');
				});
			},
			select: (start, end) => {

			},
			eventClick: (calEvent, jsEvent, view) => {
				swal({
					title: 'Are you sure?',
					text: 'You will not be able to recover this event !',
					type: 'warning',
					showCancelButton: true,
					confirmButtonText: 'Yes, delete it!',
					cancelButtonText: 'No, keep it'
				}).then(async () => {
					await this.specialDayService.DeleteSpeicalDay(calEvent.id).toPromise().then(() => {
						swal(
							'Deleted!',
							'Your event has been deleted.',
							'success'
						);
						calendar.fullCalendar('removeEvents', calEvent.id);
					}).catch(() => {
						swal(
							'Error',
							'Some error occurred,pls try again latter !',
							'error'
						)
					});

				}, (dismiss) => {
					// dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
					if (dismiss === 'cancel') {
						swal(
							'Cancelled',
							'Your event is safe :)',
							'error'
						)
					}
				});
				calendar.fullCalendar('unselect');
			},
			editable: true,
			eventLimit: true, // allow "more" link when too many events


			// color classes: [ event-blue | event-azure | event-green | event-orange | event-red ]
			events: this.listModel
		});
	}
}