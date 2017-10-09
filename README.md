# DateRangePicker
A Simple, Easy to use, Materialistic DateRangePicker library for Android.

# Screenshots
![Screenshot 1](/screenshots/screenshot1.jpeg?raw=true "From Date")   ![Screenshot 2](/screenshots/screenshot2.jpeg?raw=true "To Date")

# Usage
Add this to project level gradle file
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add dependency to app level gradle file
```
dependencies {
	compile 'com.github.VenkatramanRajagopalan:DateRangePicker:1.0'
}
```
# Example
Add following lines in the activity file
```
showCalendar.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {
		FragmentManager fragmentManager = getSupportFragmentManager(); //Initialize fragment manager
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(); // Create datePickerDialog Instance 
                datePickerDialog.show(fragmentManager,"Date Picker"); // Show DatePicker Dialog
	}
});

```
# Get From and To dates

Make activity implement OnDateRangePickedListener() and override default method
```
public class DemoActivity extends AppCompatActivity implements DatePickerDialog.DateRangePickedListener {
	...
	
	@Override
	public void OnDateRangePicked(Calendar dateFrom, Calendar dateTo) {
		Log.e("From Date",dateFrom.getTime());
		Log.e("To Date",dateTo.getTime());
	}
}
```

Or use below implementation
```
datePickerDialog.setOnDateRangePickedListener(new DatePickerDialog.DateRangePickedListener() {
        @Override
	public void OnDateRangePicked(Calendar dateFrom, Calendar dateTo) {
        	Log.e("From Date",dateFrom.getTime());
		Log.e("To Date",dateTo.getTime());   
	}
});
```


More optimizations coming soon :)
