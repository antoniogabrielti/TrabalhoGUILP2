package view;

public class VendaMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_BUSCAFILME = 3;
    public static final int OP_BUSCADATAHORA = 4;
    public static final int OP_BUSCASALA = 5;
    public static final int OP_DELETAVENDA = 6;
    public static final int OP_ATUALIZAVENDA = 7;
    public static final int OP_TOPSALAFILME = 8;
    public static final int OP_VOLTAR = 0;
  

    public static String getOpcoes() {
        return ("\n--------------------------------------\n"
                + "1- Cadastrar venda\n"
                + "2- Listar vendas\n"
                + "3- Buscar vendas por filme\n"
                + "4- Buscar vendas por data\n"
                + "5- Buscar vendas por sala\n"
                + "6- Deletar venda\n"
                + "7- Atualizar venda\n"
                + "8- Relatorio Filme ou Sala em mais vendas\n"
                + "0- Voltar"
                + "\n--------------------------------------");
    }        
}
