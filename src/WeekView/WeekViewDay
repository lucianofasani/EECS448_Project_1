package WeekView;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class WeekViewDay extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -917222811593509063L;
	

	private Integer day;
	
	public WeekViewDay(Integer dayNum, Dimension d){
		day = dayNum;
		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);
		setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel label;
		if(dayNum == null){
			label = new JLabel("");
		}else{
			label = new JLabel(dayNum+"");
		}
		label.setForeground(Color.WHITE);
		add(label);
	}
	
	public int getDay(){
		return day;
	}


}
