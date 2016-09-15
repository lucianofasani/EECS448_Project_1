package YearView;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Days.IDay;
import Records.CachedCalendar;




public class YearViewDay extends JPanel implements IDay{

	/**
	 * 
	 */
	private static final long serialVersionUID = -917222811593509063L;
	

	private Integer day;
	
	public YearViewDay(Integer dayNum, Dimension d){
		day = dayNum;
		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);
		setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel label;
		if(dayNum == null){
			label = new JLabel("");
		}else if (dayNum == 1)
                {
			label = new JLabel("January");
		}
                else if (dayNum == 2)
                {
			label = new JLabel("February");
		}
                else if (dayNum == 3)
                {
			label = new JLabel("March");
		}
                else if (dayNum == 4)
                {
			label = new JLabel("April");
		}
                else if (dayNum == 5)
                {
			label = new JLabel("May");
		}
                else if (dayNum == 6)
                {
			label = new JLabel("June");
		}
                else if (dayNum == 7)
                {
			label = new JLabel("July");
		}
                else if (dayNum == 8)
                {
			label = new JLabel("August");
		}
                else if (dayNum == 9)
                {
			label = new JLabel("September");
		}
                else if (dayNum == 10)
                {
			label = new JLabel("October");
		}
                else if (dayNum == 11)
                {
			label = new JLabel("November");
		}
                else if (dayNum == 12)
                {
			label = new JLabel("December");
		}
                else
                {
			label = new JLabel("");
		}
		label.setForeground(Color.black);
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
