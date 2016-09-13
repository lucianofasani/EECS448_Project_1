package WeekView;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Days.IDay;
import Records.CachedCalendar;




public class WeekViewDay extends JPanel implements IDay{

	/**
	 * 
	 */
	private static final long serialVersionUID = -917222811593509063L;
	

	private Integer day;
	private Integer month;
	private Integer year;
	
	public WeekViewDay(Integer dayNum,Integer monthNum,Integer yearNum, Dimension d){
		
		
		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);
		setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel label;

		if(monthNum == null && yearNum == null){
			day = dayNum;
			month = CachedCalendar.getInstance().Month;
			year = CachedCalendar.getInstance().Year;	
		}else if(monthNum < CachedCalendar.getInstance().Month){
			month = CachedCalendar.getInstance().Month - 1;
			year = CachedCalendar.getInstance().Year;
			if(month < 0){
				year -= 1;
				month = 11;
			}
			Calendar mycal = Calendar.getInstance();
			mycal.set(Calendar.MONTH, month);
			mycal.set(Calendar.YEAR, year);
			int maxOfPrevious = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
			day = maxOfPrevious + dayNum;
	
		}else{
			month = CachedCalendar.getInstance().Month + 1;
			year = CachedCalendar.getInstance().Year;
			if(month > 11){
				year += 1;
				month = 0;
			}
			Calendar mycal = Calendar.getInstance();
			mycal.set(Calendar.MONTH, month);
			mycal.set(Calendar.YEAR, year);
			day = dayNum;

		}
		if(day==null){
			label = new JLabel("");
		}else{
			label = new JLabel(day+"");
		}
		
	
		label.setForeground(Color.WHITE);
		add(label);
	}
	@Override
	public int getDay(){
		return day;
	}

	@Override
	public int getMonth() {
		return month;
	}

	@Override
	public int getYear() {
		return year;
	}


}
