package kz.el.amount;

import kz.el.check.CheckModel;

public class Amount {
	static CheckModel model = new CheckModel();
	private static int int_amount;
	
	public static int getInt_amount() {
		int row = model.getRowCount();
		int_amount = 0;
		for(int i=0;i<row; i++) {
			int foo;
			try {
			   foo = Integer.parseInt((String) model.getValueAt(i, 2));
			}
			catch (NumberFormatException e)
			{
			   foo = 0;
			}

			int_amount+=foo;
			
		}
		return int_amount;
	}

	public static String getAmount() {
			   String amount = Integer.toString(getInt_amount());
		return amount;
	}

}
