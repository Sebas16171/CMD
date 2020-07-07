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
        
        switch (Valor) {
            case "alv":
                return -1;
            case "":
        
            default:
                return 1;
        }
    }

}