import java.util.*;

public class Main {

    
    private static void Agrega_Prompt() {
        System.out.print("" + System.getProperty("user.dir") + "> ");
    }
    
    public static void main(String[] args) throws InterruptedException {
        //new Ventana();
        
        Scanner scanner = new Scanner(System.in);
        String comando = "";
        
        System.out.println("Un saludito para toda la raza que la sigue cotorreando\n");
        
        int continua = 1;

        while (continua != -1) {
            ArrayList<String> Parametros = new ArrayList<String>();
            
            Agrega_Prompt();
            comando = scanner.nextLine().trim();

            if (!comando.equals("")) {
                String[] Palabras = comando.split("\\s+");
                for (int i = 1 ; i < Palabras.length ; i++){
                    Parametros.add(Palabras[i]);
                }
    
                Comando input = new Comando(Palabras[0], Parametros);
    
                continua = input.ProcesarComando();
    
                switch (continua) {
                    case 1:
                        System.out.println("Error: Comando '" + Palabras[0] + "' no encontrado");
                        break;
                
                    default:
                        break;
                }         
            }

        }

        System.out.println("No me quiero ir Sr. Stark. :'(");
        Thread.sleep(5000);
    }
    
}