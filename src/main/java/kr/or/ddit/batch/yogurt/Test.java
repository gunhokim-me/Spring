package kr.or.ddit.batch.yogurt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.or.ddit.yogurt.model.DailyVo;

public class Test {
	
	public static void main(String[] args) {
		
		//dt : 202102, item : cid-1 , pod-100 , day-2, cnt-1
		//==> 
		//cid-1 , pod-100 , dt-20210201, cnt-1
		//cid-1 , pod-100 , dt-20210208, cnt-1
		//cid-1 , pod-100 , dt-20210215, cnt-1
		//cid-1 , pod-100 , dt-20210222, cnt-1
		
		//1일 ~ 28일 loop
		//if(요일 == item.요일과 같은지 체크)
		//     해당 일자로 일실적 데이터를 생성
		
		int day = 2;
		String dt ="202102";
		
		List<DailyVo> list = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		int firstDate = cal.getMinimum(Calendar.DAY_OF_MONTH);
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int lastDat = cal.getMaximum(Calendar.DAY_OF_MONTH);
		
		System.out.println(lastDate + "   " + lastDat);
		int days = 0;
		
		switch(day) {
		case 1:
			cal.set(Calendar.DAY_OF_MONTH, Calendar.SUNDAY);
			days = cal.get(Calendar.DAY_OF_MONTH);
		case 2:
			cal.set(Calendar.DAY_OF_MONTH, Calendar.MONDAY);
			days = cal.get(Calendar.DAY_OF_MONTH);
		case 3:
			cal.set(Calendar.DAY_OF_MONTH, Calendar.TUESDAY);
			days = cal.get(Calendar.DAY_OF_MONTH);
		case 4:
			cal.set(Calendar.DAY_OF_MONTH, Calendar.WEDNESDAY);
			days = cal.get(Calendar.DAY_OF_MONTH);
		case 5:
			cal.set(Calendar.DAY_OF_MONTH, Calendar.THURSDAY);
			days = cal.get(Calendar.DAY_OF_MONTH);
		case 6:
			cal.set(Calendar.DAY_OF_MONTH, Calendar.FRIDAY);
			days = cal.get(Calendar.DAY_OF_MONTH);
		case 7:
			cal.set(Calendar.DAY_OF_MONTH, Calendar.SATURDAY);
			days = cal.get(Calendar.DAY_OF_MONTH);
			
		}
		
		System.out.println(days);
		
		cal.set(Calendar.DAY_OF_MONTH, Calendar.SUNDAY);
		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		
	}

}
