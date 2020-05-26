package uniandes.isis2304.alohandes.negocio;

public class RecintosPorOperario implements VORecintosPorOperario {

	private long idRecinto;

	private long idOperario;

	public RecintosPorOperario(){

		idRecinto=-1;

		idOperario=-1;

	}
	public RecintosPorOperario( long pidRecinto, long pidOperario){

		idRecinto= pidRecinto;

		idOperario= pidOperario;

	}

	public long getIdRecinto() {
		// TODO Auto-generated method stub
		return idRecinto;
	}


	public long getIdOperario() {
		// TODO Auto-generated method stub
		return idOperario;
	}
	
	public void setIdRecinto(long pIdRecinto) {
		// TODO Auto-generated method stub
		 idRecinto=pIdRecinto;
	}


	public void setIdOperario(long pIdOperario) {
		// TODO Auto-generated method stub
		 idOperario=pIdOperario;
	}
	
public String toString(){
		
		return "RecintosPorOperario [idRecinto=" + idRecinto + ", idOperario=" + idOperario + "]";
	}
	
	



}
