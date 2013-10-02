package xurxo.AndroidClient;

public class Customer
{
	private String _CodCliente ="";
	private String _RazonSocial="";
	private String _Direccion="";
	private String _Telefono="";
	
	public Customer()
	{		
	}
	
	public Customer(String CodCliente, String RazonSocial, String Direccion, String Telefono)
	{
		_CodCliente =CodCliente;
		_RazonSocial=RazonSocial;
		_Direccion=Direccion;
		_Telefono=Telefono;			
	}
	
	public String GetCodCliente()
	{
		return _CodCliente;
	}
	
	public void SetCodCliente(String value)
	{
		_RazonSocial = value;
	}

	public String GetRazonSocial()
	{
		return _RazonSocial;
	}
	
	public void SetRazonSocial(String value)
	{
		_RazonSocial = value;
	}
	
	public String GetDireccion()
	{
		return _Direccion;
	}
	
	public void SetDireccion(String value)
	{
		_Direccion = value;
	}
	
	public String GetTelefono()
	{
		return _Telefono;
	}
	
	public void SetTelefono(String value)
	{
		_Telefono = value;
	}
}

