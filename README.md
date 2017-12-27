# DateRangePicker
A Simple, Easy to use, Materialistic DateRangePicker library for Android. It comes with a custom spinner which can be used to select following values.

* Today - Current date (From 00:00:00 to present)
* This week - Current week (From Monday 00:00:00 to present)
* This month - Current month (From 1st of month 00:00:00 to present)
* Custom - This will display DatePicker Dialog
	

## Screenshots
![Screenshot 1](/screenshots/screenshot1.jpeg?raw=true "From Date")   ![Screenshot 2](/screenshots/screenshot2.jpeg?raw=true "To Date")

## Usage
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
	compile 'com.github.VenkatramanRajagopalan:DateRangePicker:2.1'
}
```

## Example 1 (using DatePickerSpinner)
Add following lines in your activity_main.xml layout file
```
<com.twigsntwines.daterangepicker.DatePickerSpinner
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:spinnerText="Select From and To Dates"
        android:id="@+id/datePickerSpinner" />
```

And in your DemoActivity.class activity file
```
@Override
protected void onCreate(Bundle savedInstanceState) {
	...
	
	DatePickerSpinner spinner = (DatePickerSpinner) findViewById(R.id.spinner); // Find Datepickerspinner declared in xml file
	spinner.setDateRangePickedListener(new DateRangePickedListener() {
	    @Override
	    public void OnDateRangePicked(Calendar fromDate, Calendar toDate) {
		Log.e("From Date",fromDate.getTime());
		Log.e("To Date",toDate.getTime());
	    }
	}); // Handle results when a data is picked
}
```


## Example 2 (without using DatePickerSpinner)
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
#### Get results back in activity

Make activity implement OnDateRangePickedListener() and override default method
```
public class DemoActivity extends AppCompatActivity implements DateRangePickedListener {
	...
	
	@Override
	public void OnDateRangePicked(Calendar fromDate, Calendar toDate) {
		Log.e("From Date",fromDate.getTime());
		Log.e("To Date",toDate.getTime());
	}
}
```

Or use below implementation
```
datePickerDialog.setOnDateRangePickedListener(new DateRangePickedListener() {
        @Override
	public void OnDateRangePicked(Calendar fromDate, Calendar toDate) {
        	Log.e("From Date",fromDate.getTime());
		Log.e("To Date",toDate.getTime());   
	}
});
```
Results are in calendat format so as to provide better customization with the result. [Click here](https://developer.android.com/reference/java/util/Calendar.html "Calendar Android") to learn more about calendars in android.

More optimizations coming soon :)
