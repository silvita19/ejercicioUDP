	//Condori Lanza Silvia Eugenia 6164141Lp

package UDPej;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ServidorUDP {
	DatagramSocket socketUDP;
	int PORT;
	public ServidorUDP(int p) {
		PORT = p;
	}
	public void unicadena() 
	{
		try {
			socketUDP = new DatagramSocket(PORT);
			System.out.println(" ----- SERVIDOR UDP INICIADO -----");
			System.out.println("---- Esperando Solicitudes ----");
			
			while(true){
				/////////////////////RECIBIMOS EL 1er DATAGRAMA DEL CLIENTE////////////////////////
				DatagramPacket paqueteRecibido = new DatagramPacket(new byte[1024],1024); //Creamos el datagrama, aqui recibiremos el datagrama del cliente
				socketUDP.receive(paqueteRecibido); //Recibimos el datagrama del cliente
				String mensajeRecibido =recorte( new String(paqueteRecibido.getData())); // Convertimos el datagrama en cadena, utilizamos el metodo recorte(). para eliminar '#' al final de la cadena
				//int a = Integer.parseInt(mensajeRecibido); //convertimos la cadena en entero
				////////////////////////////ENVIAMOS LA RESPUESTA AL CLIENTE//////////////////////////
				byte mensajeEnviar[] = new byte[5]; //Creamos el byte donde guardaremos el mensaje
				int palabra = mensajeRecibido.length();
				String ans = "Respuesta del servidor:  "+palabra+"#"; // La cadena de respuesta del servidor.
				mensajeEnviar = ans.getBytes();//convertimos la cadena en bytes y lo guardamos en el vector de bytes
				DatagramPacket paqueteAEnviar = new DatagramPacket(mensajeEnviar,mensajeEnviar.length,paqueteRecibido.getAddress(),paqueteRecibido.getPort());//Creamos el datagrama con nuestro mensaje
				socketUDP.send(paqueteAEnviar);//Enviamos el datagrama al cliente
				//System.out.println("r1"+ans);
				///////////////////////////////////////////////////////////////////////////////////////
				/////////////IMPRIMOS EN CONSOLA DEL SERVIDOR////////////////////////
				System.out.println("Datos introducidos por el cliente: "+mensajeRecibido);
			}
		}catch(Exception e)
			{ 	
			e.printStackTrace();
			}		
	}
	public void finalizar(){
		try{
			socketUDP.close();
			System.out.println("Conexion Finalizada...!!!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public String recorte(String s){
		String m="";
		char c[]=s.toCharArray();
		boolean sw=true;
		for(int i=0;i<s.length()&&sw;i++){
			if(c[i]!='#'){
				m=m+c[i];
			}else{
				sw=false;
			}
		}
		return m;
	}	
}
