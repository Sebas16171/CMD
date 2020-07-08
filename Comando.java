import java.util.*;

public class Comando {
    private String Valor;
    ArrayList<String> Parametros = new ArrayList<String>();

    public Comando(String iValor, ArrayList<String> iParametros){
        Valor = iValor;
        Parametros = iParametros;

        //Command_save_debug();

    }
    
    public void Command_save_debug(){
        System.out.println("Se registro un comando con el valor: " + Valor + "\n" + 
                            "Y con " + Parametros.size() + " parametros");
        for (String Parametro : Parametros){
            System.out.println(Parametro);
        }
   
    }
    
    public String GetValor(){
        return Valor;
    }

    public ArrayList<String> GetParametros(){
        return Parametros;
    }

    public int ProcesarComando(){

        //  Return -1      Saliendo
        //  Return 0       Ok
        //  Return 1       Error    Comando no encontrado
        //  Return 2       Error    La cantidad de parametros no coincide
        //  Return 3       Error    El parametro no coincide con el tipo requerido
        //  Return 4       Error    La variable ya existe 
        
        switch (Valor) {
            case "alv":
                return -1;
            case "NwP":
                String Nuevo_Prompt = "";
                for (String Palabra : Parametros){
                    Nuevo_Prompt += Palabra + " ";
                }
                Main.Cambiar_Prompt(Nuevo_Prompt);
                break;
            case "Var":            
                if (Parametros.size() != 2) {
                    Main.Salida_Extra[0] = "2";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                } else{
                    String iNombre = Parametros.get(0);
                    float iValor = 0;
                    
                    try {
                        iValor = Float.valueOf(Parametros.get(1));
                    } catch (Exception e) {
                        Main.Salida_Extra[0] = "numerico";
                        return 3;
                    }

                    for (Variable varEnlistada : Main.Variables) {
                        if (varEnlistada.GetNombre().equals(iNombre)) {
                            return 4;
                        }
                    }           

                    Main.Variables.add(new Variable(iNombre, iValor));
                }
                break;
            default:
                return 1;
        }
        return 0;
    }

}