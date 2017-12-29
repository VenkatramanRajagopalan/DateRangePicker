package com.twigsntwines.daterangepicker;

import java.util.Calendar;

public interface DateRangePickedListener {

    void OnDateRangePicked(Calendar fromDate, Calendar toDate);

    void OnDatePickCancelled();

}
