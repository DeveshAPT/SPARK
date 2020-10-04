package ScriptHelper;

 public  class ServiceOrders 
{
	public static String PrimaryServiceOrder="";
	public  static String SubServiceOrder="";
	public static String getPrimaryServiceOrder()
	{
		return  PrimaryServiceOrder;
	}
	
	public static String getSubServiceOrder()
	{
		return  SubServiceOrder;
	}
	public static void setPrimaryServiceOrder(String order)
	{
		PrimaryServiceOrder=order;
	}
	public static void setSubServiceOrder(String suborder)
	{
		SubServiceOrder=suborder;
	}
}
