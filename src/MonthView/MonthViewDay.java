package MonthView;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Days.IDay;
import Records.CachedCalendar;




public class MonthViewDay extends JPanel implements IDay{

	/**
	 * 
	 */
	private static final long serialVersionUID = -917222811593509063L;
	

	private Integer day;
	
	public MonthViewDay(Integer dayNum, Dimension d){
		day = dayNum;
		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);
		setBorder(BorderFactory.createLineBorder(Color.white));
		JLabel label;
		if(dayNum == null){
			label = new JLabel("");
		}else{
			label = new JLabel(dayNum+"");
		}
		label.setForeground(Color.white);
		add(label);
	}
	
	@Override
	public int getDay(){
		return day;
	}

	@Override
	public int getMonth() {
		return CachedCalendar.getInstance().Month;
	}

	@Override
	public int getYear() {
		return CachedCalendar.getInstance().Year;
	}



}
