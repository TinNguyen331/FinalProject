if ('undefined' !== typeof module) {
    inNotify = true;
    type = ['', 'info', 'success', 'warning', 'danger'];
    module.exports = function initNotify(from, align) {
        color = Math.floor((Math.random() * 4) + 1);
        if (inNotify) {
            $.notify({
                icon: "notifications",
                message: "Welcome to <b>FLS web application</b>"

            }, {
                type: type[color],
                timer: 3000,
                placement: {
                    from: from,
                    align: align
                }
            });
            inNotify = false;
        }
    }
}