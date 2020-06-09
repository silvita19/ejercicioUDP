	//Condori Lanza Silvia Eugenia 6164141Lp

package UDPej;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
public class ClienteUDP {
	int puerto;
	InetAddress servidorDestino;
	DatagramSocket socketUDP;
	DatagramPacket paqueteRecibido;
	DatagramPacket paqueteRecibido1;
	DatagramPacket paqueteAEnviar;
	private Scanner sc;
	
	public ClienteUDP(String h,int p) throws UnknownHostException {
		servidorDestino = InetAddress.getByName(h);
		puerto = p;
	}
	public void unicadena()
	{
		try {
			sc = new Scanner(System.in);
			socketUDP = new DatagramSocket();//creamos el socket UDP 			
			System.out.println("*********CONEXION UDP INICIADA************");
			System.out.println("******************************************");
			System.out.println("*************INGRESAR CADENA**************");
			System.out.println("*****");
			////////////////////////ENVIAMOS EL UNICO DATAGRAMA///////////////////////////////////////////
			System.out.print("Cliente:  ");
			String cadena = sc.nextLine()+"#"; //Cualquier cadena que mandemos al servidor debe tener '#' al final 
			byte mensajeE[] = new byte[1024];// Creamos el vector de bytes donde guardaremos nuestro mensaje
			mensajeE = cadena.getBytes();//Convertimos la cadena en byte  
			DatagramPacket numero1 = new DatagramPacket(mensajeE,mensajeE.length,servidorDestino,puerto);//Creamos el datagrama con nuestro mensaje y datos del servidor
			socketUDP.send(numero1);//enviamos el datagrama
			////////////////////////RECIBIMOS EL DATAGRAMA ENVIADO POR EL SERVIDOR/////////////////////////
			byte mensajeR[] = new byte[1024];//Creamos el vector de bytes donde recibiremos la respuesta del servidor
			paqueteRecibido = new DatagramPacket(mensajeR,mensajeR.length);//Creamos el datagrama
			socketUDP.receive(paqueteRecibido);//Recibimos el datagrama del servidor
			String mensajeRecibido = new String(paqueteRecibido.getData());//Convertimos el datagrama en cadena    
			/////////////////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////IMPRIMIMOS EL MENSAJE DEL SERVIDOR///////////////////////////////////
			System.out.println(recorte(mensajeRecibido));//La cadena que recibimos de parte del servidor tiene un '#' al final asi que lo recortamos con el metodo 'recorte()'
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void finalizar(){
		try {
			socketUDP.close();
			System.out.println("Conexion Finalizada...!!!");
		} catch (Exception e) {
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