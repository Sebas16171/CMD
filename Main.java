import java.util.*;

public class Main {

    private static String prompt = "";
    public static String[] Salida_Extra = new String[20];
    
    public static ArrayList<Variable> Variables = new ArrayList<Variable>();

    private static void Agrega_Prompt() {
        if (prompt.equals("")){
            System.out.print("" + System.getProperty("user.dir") + "> ");
        } else {
            System.out.print(prompt + "> ");
        }
    }

    public static void Cambiar_Prompt(String Nuevo_Prompt) {
        prompt = Nuevo_Prompt.trim();
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        Scanner scanner = new Scanner(System.in);
        String comando = "";
        
        System.out.println("\n\nUn saludito para toda la raza que la sigue cotorreando ");
        System.out.println("======Autor: Sebastian Cervera N. - Version: 2.1======\n");
        
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
                    case 2:
                        System.out.println("Error: " + Salida_Extra[0] + " parametro(s) esperado(s), " + Salida_Extra[1] + " recibido(s)");
                        break;
                    case 3:
                        System.out.println("Error: El parametro debe ser de tipo " + Salida_Extra[0]);
                        break;
                    case 4:
                        System.out.println("Error: La variable '" + Parametros.get(0) + "' ya fue registrada");
                        break;
                    case 5:
                        System.out.println("Error: El nombre de una variable no puede iniciar por un numero");
                        break;
                    case 6:
                        System.out.println("Aviso: Ninguna variable registrada");
                        break;
                    case 7:
                        System.out.println("Error: Variable '" + Salida_Extra[0] + "' no encontrada");
                        break;
                
                    default:
                        break;
                }
                if (Palabras[0].trim().equals("lstVar")){
                    System.out.print(Salida_Extra[0]);
                }   
            }

        }

        scanner.close();
        System.out.println("No me quiero ir Sr. Stark. :'(");
        Thread.sleep(3000);
    }
    
}