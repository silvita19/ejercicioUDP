//Condori Lanza Silvia Eugenia 6164141Lp


package UDPej;
import java.io.IOException;
public class MainClienteUDP {
	public static void main(String[] args) throws IOException {
		
		ClienteUDP A=new ClienteUDP("localhost",8888);
		A.unicadena();
	}
}
