document.addEventListener('DOMContentLoaded', function() {
    var dateInput = document.querySelector('.date-input');
    
    dateInput.addEventListener('change', function() {
        var selectedDate = new Date(this.value);
        var day = selectedDate.getDate().toString().padStart(2, '0');
        var month = (selectedDate.getMonth() + 1).toString().padStart(2, '0'); // Monate sind 0-basiert
        var year = selectedDate.getFullYear();
        
        var formattedDate = day + '.' + month + '.' + year;
        
        this.value = formattedDate;
    });
});