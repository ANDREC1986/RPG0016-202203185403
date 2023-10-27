/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrodb;

import cadastrodb.model.PessoaFisica;
import cadastrodb.model.PessoaFisicaDAO;
import cadastrodb.model.PessoaJuridica;
import cadastrodb.model.PessoaJuridicaDAO;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class CadastroDB {
        private static boolean mainloop = true;
        private static boolean navigate = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        do {
                navigate = true;
                System.out.println("""
                           ===============================
                           1 - Incluir Pessoa
                           2 - Alterar Pessoa1
                           3 - Excluir Pessoa
                           4 - Buscar pelo Id
                           5 - Exibir Todos
                           0 - Finalizar Programa
                           ===============================
                           """);
                Scanner select = new Scanner(System.in);
                String menu = select.nextLine();
                switch(menu) {
                    case "1" -> {
                    String tipo = tipo();
                    Scanner pessoa = new Scanner(System.in);
                    switch(tipo) {
                        case "f" -> {
                            PessoaFisica nova = new PessoaFisica("",0,"","","","","","");
                            System.out.println("Informe o Nome");
                            nova.setNome(pessoa.nextLine());
                            System.out.println("Informe o CPF");
                            nova.setCpf(pessoa.nextLine());
                            System.out.println("Informe o Logradouro");
                            nova.setLogradouro(pessoa.nextLine());
                            System.out.println("Informe o Cidade");
                            nova.setCidade(pessoa.nextLine());
                            System.out.println("Informe o Estado");
                            nova.setEstado(pessoa.nextLine());
                            System.out.println("Informe o Telefone");
                            nova.setTelefone(pessoa.nextLine());
                            System.out.println("Informe o E-mail");
                            nova.setEmail(pessoa.nextLine());
                            PessoaFisicaDAO.incluir(nova);
                            pause();
                        }
                        case "j" -> {
                            PessoaJuridica nova = new PessoaJuridica("",0,"","","","","","");
                            System.out.println("Informe o Nome");
                            nova.setNome(pessoa.nextLine());
                            System.out.println("Informe o CPF");
                            nova.setCnpj(pessoa.nextLine());
                            System.out.println("Informe o Logradouro");
                            nova.setLogradouro(pessoa.nextLine());
                            System.out.println("Informe o Cidade");
                            nova.setCidade(pessoa.nextLine());
                            System.out.println("Informe o Estado");
                            nova.setEstado(pessoa.nextLine());
                            System.out.println("Informe o Telefone");
                            nova.setTelefone(pessoa.nextLine());
                            System.out.println("Informe o E-mail");
                            nova.setEmail(pessoa.nextLine());
                            PessoaJuridicaDAO.incluir(nova);
                            pause();
                        }
                    }
                    }
                    case "2" -> {
                        String tipo = tipo();
                        Scanner id = new Scanner(System.in);
                        switch(tipo) {
                            case "f" -> {
                                System.out.println("Informe o ID");
                                String pessoaid = id.nextLine();
                                try {
                                    Integer.parseInt(pessoaid);
                                } catch(NumberFormatException ex) {
                                    System.out.println("ID Invalida");
                                    break;                                    
                                }
                                PessoaFisica editar = PessoaFisicaDAO.getPessoa(Integer.parseInt(pessoaid));
                                System.out.println("Qual dado deseja editar? Nome, CPF, Cidade, Estado, Telefone ou Email");
                                Scanner getParam = new Scanner(System.in);
                                String param = getParam.nextLine().toLowerCase();
                                Scanner getValue = new Scanner(System.in);
                                String value = "";
                                switch(param) {
                                    case "nome" -> {
                                        System.out.println("Insira o nome:");
                                        value = getValue.nextLine();                                    
                                    }
                                    case "cpf" -> {
                                        System.out.println("Insira o CPF (11 Digitos):");
                                         value = getValue.nextLine();                                    
                                    }
                                    case "cidade" -> {
                                        System.out.println("Insira a Cidade:");
                                         value = getValue.nextLine();                                    
                                    }
                                    case "estado" -> {
                                        System.out.println("Insira o estado (2 Characteres):");
                                        value = getValue.nextLine();                                    
                                    }
                                    case "telefone" -> {
                                        System.out.println("Insira a telefone (11 Digitos):");
                                        value = getValue.nextLine();                                    
                                    }
                                    case "email" -> {
                                        System.out.println("Insira o Email:");
                                        value = getValue.nextLine();                                    
                                    }
                                    default -> {
                                        System.out.println("Dado Invalido!");
                                        pause();
                                    }
                                }
                                PessoaFisicaDAO.alterar(Integer.parseInt(pessoaid), param, value);
                                pause();
                            }
                            case "j" -> {
                                System.out.println("Informe o ID");
                                String pessoaid = id.nextLine();
                                try {
                                    Integer.parseInt(pessoaid);
                                } catch(NumberFormatException ex) {
                                    System.out.println("ID Invalida");
                                    break;                                    
                                }
                                PessoaFisica editar = PessoaFisicaDAO.getPessoa(Integer.parseInt(pessoaid));
                                System.out.println("Qual dado deseja editar? Nome, CNPJ, Cidade, Estado, Telefone ou Email");
                                Scanner getParam = new Scanner(System.in);
                                String param = getParam.nextLine().toLowerCase();
                                Scanner getValue = new Scanner(System.in);
                                String value = "";
                                switch(param) {
                                    case "nome" -> {
                                        System.out.println("Insira o nome:");
                                        value = getValue.nextLine();                                    
                                    }
                                    case "cpf" -> {
                                        System.out.println("Insira o CNPJ (15 Digitos):");
                                         value = getValue.nextLine();                                    
                                    }
                                    case "cidade" -> {
                                        System.out.println("Insira a Cidade:");
                                         value = getValue.nextLine();                                    
                                    }
                                    case "estado" -> {
                                        System.out.println("Insira o estado (2 Characteres):");
                                        value = getValue.nextLine();                                    
                                    }
                                    case "telefone" -> {
                                        System.out.println("Insira a telefone (11 Digitos):");
                                        value = getValue.nextLine();                                    
                                    }
                                    case "email" -> {
                                        System.out.println("Insira o Email:");
                                        value = getValue.nextLine();                                    
                                    }                               
                                    default -> {
                                        System.out.println("Dado Invalido!");
                                        pause();
                                    }
                                }
                                PessoaFisicaDAO.alterar(Integer.parseInt(pessoaid), param, value);
                                pause();
                            }
                        }
                    }
                    case "3" -> {
                    String tipo = tipo();
                    System.out.println("Informe o ID");
                    Scanner getid = new Scanner(System.in);
                    String id = getid.nextLine();
                    switch(tipo){
                        case "f" -> {
                            try {
                                Integer.parseInt(id);
                                } catch(NumberFormatException ex) {
                                    System.out.println("ID Invalida");
                                    break;                                    
                                }
                            PessoaFisicaDAO.excluir(Integer.parseInt(id));
                            pause();
                        }
                        case "j" -> {
                            try {
                                    Integer.parseInt(id);
                                } catch(NumberFormatException ex) {
                                    System.out.println("ID Invalida");
                                    break;                                    
                                }
                            PessoaJuridicaDAO.excluir(Integer.parseInt(id));
                            pause();
                        }
                    }
                    }
                    case "4" -> {
                        String tipo = tipo();
                        Scanner getid = new Scanner(System.in);
                        System.out.println("Informe o ID:");
                        String id = getid.nextLine();
                            try {
                                Integer.parseInt(id);
                                } catch(NumberFormatException ex) {
                                    System.out.println("ID Invalida");
                                    break;                                    
                                }
                        switch(tipo) {
                            case "f" -> {
                                 PessoaFisica alvo = PessoaFisicaDAO.getPessoa(Integer.parseInt(id));
                                 alvo.exibir();
                                 pause();
                            }
                            case "j" -> {
                                PessoaJuridica alvo = PessoaJuridicaDAO.getPessoa(Integer.parseInt(id));
                                alvo.exibir();
                                pause();
                            }
                        
                        }
                    
                    }
                    case "5" -> {
                    String tipo = tipo();
                    switch(tipo) {
                        case "f" -> {
                        ArrayList<PessoaFisica> pessoasfisicas = PessoaFisicaDAO.getPessoas();
                        pessoasfisicas.forEach((e) -> e.exibir());
                        pause();
                        }
                        case "j" -> {
                        ArrayList<PessoaJuridica> pessoasjuridicas = PessoaJuridicaDAO.getPessoas();
                        pessoasjuridicas.forEach((e) -> e.exibir());
                        pause();
                        }
                    }
                    }
                    case "0" -> {
                    mainloop = false;
                    }
                        
                }
        } while (mainloop == true);
    }
    
    
    
    public static String tipo(){
        String tipo = null;
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica | R - Retornar");
        while(tipo == null) {
            Scanner select = new Scanner(System.in);
            String opt = select.nextLine();            
            switch(opt) {
                case "f" -> {
                tipo = "f";
                } 
                case "j" -> {
                tipo = "j";
                }
                case "r" ->{
                navigate = false;
                tipo = "quit";
                }
                default -> {
                System.out.println("Tipo invalido!");
                }
            }
        }
        return tipo;
    }
    
    public static void pause() {
        System.out.println("Insira R para retornar!");
        Scanner imput = new Scanner(System.in);
        while (navigate == true)
        {
            if("r".equals(imput.nextLine().toLowerCase())) {
            navigate = false;
            }
        }
    }
}