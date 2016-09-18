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
             
                int year = CachedCalendar.getInstance().Year;
                
		if(dayNum == null){
			label = new JLabel("");
		}else if (dayNum == 1)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>January<br><font color = 'white'>11 12 13 14 15</font> 1<font color = 'white'>1</font> 2<font color = 'white'>1</font><br>3<font color = 'white'>1</font> 4<font color = 'white'>1</font> 5 <font color = 'white'>1</font>6 <font color = 'white'>1</font>7 <font color = 'white'>1</font>8<font color = 'white'>1</font> 9<font color = 'white'>1</font><br>10 11 12 13 14 15 16<br>17 18 19 20 21 22 23<br> 24 25 26 27 28 29 30<br> 31</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>January<br>1<font color = 'white'>1</font> 2<font color = 'white'>1</font> 3<font color = 'white'>1</font> 4<font color = 'white'>1</font> 5 <font color = 'white'>1</font>6 <font color = 'white'>1</font>7 <font color = 'white'>1</font><br>8<font color = 'white'>1</font> 9<font color = 'white'>1</font>10 11 12 13 14<br>15 16 17 18 19 20 21<br>22 23 24 25 26 27 28<br> 29 30 31</body></html>");
                        }
		}
                else if (dayNum == 2)
                {
                        
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>February<br><font color = 'white'>0 0</font>1 <font color = 'white'>0</font> 2 <font color = 'white'>0</font> 3 <font color = 'white'>0</font>4 <font color = 'white'>0</font>5 <font color = 'white'>0</font>6 <br>7 <font color = 'white'>0</font>8 <font color = 'white'>0</font>9 <font color = 'white'>0</font>10 11 12 13 <br> 14 15 16 17 18 19 20 <br> 21 22 23 24 25 26 27 <br> 28 29</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>February<br><font color = 'white'>11 12 13 </font>1 <font color = 'white'>0</font> 2 <font color = 'white'>0</font> 3 <font color = 'white'>0</font>4 <font color = 'white'>0</font><br>5 <font color = 'white'>0</font> 6<font color = 'white'>1 </font> 7 <font color = 'white'>0</font>8 <font color = 'white'>0</font>9 <font color = 'white'>0</font>10 11<br> 12 13 14 15 16 17 18<br>19 20 21 22 23 24 25 <br>26 27 28 </body></html>");
                        }
                }
                else if (dayNum == 3)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>March<br><font color = 'white'>11 12 </font>1<font color = 'white'>1 </font>2<font color = 'white'>1 </font>3<font color = 'white'>1 </font>4<font color = 'white'>1 </font>5<font color = 'white'>1 </font><br>6<font color = 'white'>1 </font>7<font color = 'white'>1 </font>8<font color = 'white'>1 </font>9<font color = 'white'>1 </font> 10 11 12 <br> 13 14 15 16 17 18 19 <br> 20 21 22 23 24 25 26 <br> 27 28 29 30 31</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>March<br><font color = 'white'>11 12 13 </font>1<font color = 'white'>1 </font>2<font color = 'white'>1 </font>3<font color = 'white'>1 </font>4<font color = 'white'>1 </font><br>5<font color = 'white'>1 </font>6<font color = 'white'>1 </font>7<font color = 'white'>1 </font>8<font color = 'white'>1 </font>9<font color = 'white'>1 </font> 10 11 <br> 12 13 14 15 16 17 18 <br>19 20 21 22 23 24 25 <br> 26 27 28 29 30 31</body></html>");
                        }
		}
                else if (dayNum == 4)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label = new JLabel();
                        label.setText("<html><body>April<br><font color = 'white'>11 12 13 14 15</font> 1<font color = 'white'>1</font> 2<font color = 'white'>1</font><br>3<font color = 'white'>1</font> 4<font color = 'white'>1</font> 5 <font color = 'white'>1</font>6 <font color = 'white'>1</font>7 <font color = 'white'>1</font>8<font color = 'white'>1</font> 9<font color = 'white'>1</font><br>10 11 12 13 14 15 16<br>17 18 19 20 21 22 23<br> 24 25 26 27 28 29 30</body></html>");

                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>April<br><font color = 'white'>11 12 13 14 15 16</font> 1<font color = 'white'>1</font><br> 2<font color = 'white'>1</font> 3<font color = 'white'>1</font> 4<font color = 'white'>1</font> 5 <font color = 'white'>1</font>6 <font color = 'white'>1</font>7 <font color = 'white'>1</font>8<font color = 'white'>1</font><br> 9<font color = 'white'>1</font> 10 11 12 13 14 15 <br>16 17 18 19 20 21 22 <br>23 24 25 26 27 28 29<br> 30</body></html>");
                        }
		}
                else if (dayNum == 5)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>May<br>1<font color = 'white'>1 </font>2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> <br> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 11 12 13 14 <br> 15 16 17 18 19 20 21 <br> 22 23 24 25 26 27 28 <br> 29 30 31</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>May<br><font color = 'white'>11 </font>1<font color = 'white'>1 </font>2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font><br> 7<font color = 'white'>1 </font>8 <font color = 'white'>1 </font> 9<font color = 'white'>1</font>10 11 12 13 <br>14 15 16 17 18 19 20<br>21 22 23 24 25 26 27 <br>28 29 30 31</body></html>");
                        }
		}
                else if (dayNum == 6)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>June<br><font color = 'white'>11 12 12 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> <br> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 11 <br> 12 13 14 15 16 17 18 <br> 19 20 21 22 23 24 25 <br> 26 27 28 29 30</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>June<br><font color = 'white'>11 12 12 13 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font><br> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 <br> 11 12 13 14 15 16 17 <br>18 19 20 21 22 23 24<br> 25 26 27 28 29 30</body></html>");
                        }
		}
                else if (dayNum == 7)
                {
                        if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>July<br><font color = 'white'>11 12 13 14 15 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> <br>3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font><br>10 11 12 13 14 15 16<br>17 18 19 20 21 22 23<br> 24 25 26 27 28 29</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>July<br><font color = 'white'>11 12 13 14 15 16</font> 1<font color = 'white'>1</font><br> 2<font color = 'white'>1</font> 3<font color = 'white'>1</font> 4<font color = 'white'>1</font> 5 <font color = 'white'>1</font>6 <font color = 'white'>1</font>7 <font color = 'white'>1</font>8<font color = 'white'>1</font><br> 9<font color = 'white'>1</font> 10 11 12 13 14 15 <br>16 17 18 19 20 21 22 <br>23 24 25 26 27 28 29<br> 30 31</body></html>");
                        }
		}
                else if (dayNum == 8)
                {
                        if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>August<br><font color = 'white'>11 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> <br>7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 11 12 13 <br>14 15 16 17 18 19 20 <br>21 22 23 24 25 26 27 <br>28 29 30 31</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>August<br><font color = 'white'>11 12 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> <br>6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 11 12 <br> 13 14 15 16 17 18 19 <br> 20 21 22 23 24 25 26<br> 27 28 29 30 31</body></html>");
                        }
		}
                else if (dayNum == 9)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>September<br><font color = 'white'>11 12 13 14 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font><br>4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 <br>11 12 13 14 15 16 17 <br>18 19 20 21 22 23 24 <br>25 26 27 28 29 30</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>September<br><font color = 'white'>11 12 13 14 15 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> <br>3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> <br>10 11 12 13 14 15 16 <br>17 18 19 20 21 22 23 <br>24 25 26 27 28 29 30</body></html>");
                        }
		}
                else if (dayNum == 10)
                {if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>October<br><font color = 'white'>11 12 13 14 15 16 </font>1<font color = 'white'>1 </font><br>2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font><br>9<font color = 'white'>1 </font> 10 11 12 13 14 15 <br>16 17 18 19 20 21 22<br>23 24 25 26 27 28 29<br> 30 31</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>October<br>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> <br>8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 11 12 13 14 <br>15 16 17 18 19 20 21<br> 22 23 24 25 26 27 28 <br>29 30 31</body></html>");
                        }
		}
                else if (dayNum == 11)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>November<br><font color = 'white'>11 12 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font><br>6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 11 12<br>13 14 15 16 17 18 19<br>20 21 22 23 24 25 26<br>27 28 29 30</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>November<br><font color = 'white'>11 12 13 </font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> <br>5<font color = 'white'>1 </font>6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 11 <br>12 13 14 15 16 17 18 <br>19 20 21 22 23 24 25 <br>26 27 28 29 30</body></html>");
                        }
		}
                else if (dayNum == 12)
                {
			if(year == 2016)
                        {
                        label = new JLabel();
                        label.setText("<html><body>December<br><font color = 'white'>11 12 13 14</font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> 3<font color = 'white'>1 </font> <br>4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> 10 <br>11 12 13 14 15 16 17 <br> 18 19 20 21 22 23 24 <br> 25 26 27 28 29 30 31</body></html>");
                        }
                        else
                        {
                        label = new JLabel();
                        label.setText("<html><body>December<br><font color = 'white'>11 12 13 14 15</font>1<font color = 'white'>1 </font> 2<font color = 'white'>1 </font> <br>3<font color = 'white'>1 </font> 4<font color = 'white'>1 </font> 5<font color = 'white'>1 </font> 6<font color = 'white'>1 </font> 7<font color = 'white'>1 </font> 8<font color = 'white'>1 </font> 9<font color = 'white'>1 </font> <br>10 11 12 13 14 15 16 <br>17 18 19 20 21 22 23 <br>24 25 26 27 28 29 30 <br>31</body></html>");
                        }
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
