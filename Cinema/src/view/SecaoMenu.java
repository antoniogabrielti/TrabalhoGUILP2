package view;

public class SecaoMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_BUSCAFILME = 3;
    public static final int OP_BUSCADATAHORA = 4;
    public static final int OP_BUSCASALA = 5;
    public static final int OP_ATUALIZASECAO = 6;
    public static final int OP_DELETESECAO = 7;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar secao\n"
                + "2- Listar secoes\n"
                + "3- Buscar secoes por filme\n"
                + "4- Buscar secoes por horario\n"
                + "5- Buscar secoes por sala\n"
                + "6- Atualizar dados de uma secao\n"
                + "7- Excluir uma secao\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }        
}
