import { Component, OnInit } from '@angular/core';

import { UserService } from '../../service/UserService/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers:[UserService]
  
})
export class HomeComponent implements OnInit {

  user:any;
  constructor(private userService:UserService) { 
    
  }

  ngOnInit() {
    this.userService.GetInfoUser().subscribe((response:any)=>{
      this.user=response;
      console.log(response);
    });
  }

}
