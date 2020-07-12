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
        //  Return 5       Error    Nombre de la variable inicia con numero
        //  Return 6       Aviso    Ninguna variable registrada
        //  Return 7       Error    Variable no encontrada
        
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
                if (Parametros.size() != 2 && Parametros.size() != 1) {
                    Main.Salida_Extra[0] = "2";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                } else{
                    Variable tempVar = null;
                    String iNombre = Parametros.get(0);
                    float iValor = 0;

                    if (Parametros.size() == 2) {
                        try {
                            iValor = Float.valueOf(Parametros.get(1));
                        } catch (Exception e) {
                            Main.Salida_Extra[0] = "numerico";
                            return 3;
                        }
                    }

                    for (Variable varEnlistada : Main.Variables) {
                        if (varEnlistada.GetNombre().equals(iNombre)) {
                            return 4;
                        }
                    }
                    
                    if (Character.isDigit(iNombre.charAt(0))){
                        return 5;
                    }
                    
                    tempVar = new Variable(iNombre, iValor);
                    Main.Variables.add(tempVar);
                }
            break;

            case "lstVar":
                if (Main.Variables.size() != 0) {
                    String Salida = "";
                    
                    for (Variable CheckingVar : Main.Variables) {
                        Salida += CheckingVar.GetNombre() + "\t|\t" + CheckingVar.GetValor() + "\n";
                    }
                    Main.Salida_Extra[0] = Salida;
                } else{
                    return 6;
                }
            break;

            case "cng":
                if (Parametros.size() != 2 && Parametros.size() != 1) {
                    Main.Salida_Extra[0] = "2";
                    Main.Salida_Extra[1] = Parametros.size() + "";
                    return 2;
                } else {
                    Variable WorkingVar = null;
                    Variable WorkingVar2 = null;
                    float nwValor = 0;
                    for (Variable checkingVar : Main.Variables ) {
                        if (checkingVar.GetNombre().equals(Parametros.get(0))) {
                            WorkingVar = checkingVar;
                            break;
                        }                        
                    }
                    if (WorkingVar == null) {
                        Main.Salida_Extra[0] = Parametros.get(0);
                        return 7;
                    }

                    if (!Character.isDigit(Parametros.get(1).charAt(0))) {
                        System.out.println("Inica con char");
                        for (Variable checkingVar2 : Main.Variables) {
                            System.out.println("Comparo " + checkingVar2.GetNombre() + " con " + Parametros.get(1));
                            if (checkingVar2.GetNombre().equals(Parametros.get(0))) {
                                System.out.println("Encontrado");
                                WorkingVar2 = checkingVar2;
                                break;
                            }
                        }
                        System.out.println("Si no es null");
                        if (WorkingVar2 != null) {
                            System.out.println("nwValor = " + WorkingVar2.GetValor());
                            nwValor = WorkingVar2.GetValor();
                        } else{
                            Main.Salida_Extra[0] = "numerico o variable";
                            return 3;
                        }                 
                    } else {
                        nwValor = Float.valueOf(Parametros.get(1));
                    } 
                    WorkingVar.SetValor(nwValor);
                }
            break;

            default:
                return 1;
        }
        return 0;
    }

}