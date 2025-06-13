package mainapp;

import DAO.*;
import entity.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner; // Importar a classe Scanner

public class Main {
    public static void main(String[] args) {

        DeusesDAO deusesDAO = new DeusesDAO();
        SeguidorDAO seguidorDAO = new SeguidorDAO();
        TemploDAO temploDAO = new TemploDAO();
        RitualDAO ritualDAO = new RitualDAO();
        EsferaPoderDAO esferaPoderDAO = new EsferaPoderDAO();

        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n--- Gerenciamento de Entidades Mitológicas ---");
            System.out.println("1. Adicionar Deus");
            System.out.println("2. Listar Deuses");
            System.out.println("3. Atualizar Deus");
            System.out.println("4. Deletar Deus");
            System.out.println("----------------------------------------------");
            System.out.println("5. Adicionar Seguidor");
            System.out.println("6. Listar Seguidores");
            System.out.println("7. Atualizar Seguidor");
            System.out.println("8. Deletar Seguidor");
            System.out.println("----------------------------------------------");
            System.out.println("9. Adicionar Templo");
            System.out.println("10. Listar Templos");
            System.out.println("11. Atualizar Templo");
            System.out.println("12. Deletar Templo");
            System.out.println("----------------------------------------------");
            System.out.println("13. Adicionar Ritual");
            System.out.println("14. Listar Rituais");
            System.out.println("15. Atualizar Ritual");
            System.out.println("16. Deletar Ritual");
            System.out.println("----------------------------------------------");
            System.out.println("17. Adicionar Esfera de Poder");
            System.out.println("18. Listar Esferas de Poder");
            System.out.println("19. Atualizar Esfera de Poder");
            System.out.println("20. Deletar Esfera de Poder");
            System.out.println("----------------------------------------------");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        cadastrarDeus(scanner, deusesDAO);
                        break;
                    case 2:
                        listarDeuses(deusesDAO);
                        break;
                    case 3:
                        atualizarDeus(scanner, deusesDAO);
                        break;
                    case 4:
                        deletarDeus(scanner, deusesDAO);
                        break;
                    case 5:
                        cadastrarSeguidor(scanner, seguidorDAO, deusesDAO);
                        break;
                    case 6:
                        listarSeguidores(seguidorDAO);
                        break;
                    case 7:
                        atualizarSeguidor(scanner, seguidorDAO, deusesDAO);
                        break;
                    case 8:
                        deletarSeguidor(scanner, seguidorDAO);
                        break;
                    case 9:
                        cadastrarTemplo(scanner, temploDAO, deusesDAO);
                        break;
                    case 10:
                        listarTemplos(temploDAO);
                        break;
                    case 11:
                        atualizarTemplo(scanner, temploDAO, deusesDAO);
                        break;
                    case 12:
                        deletarTemplo(scanner, temploDAO);
                        break;
                    case 13:
                        cadastrarRitual(scanner, ritualDAO, deusesDAO);
                        break;
                    case 14:
                        listarRituais(ritualDAO);
                        break;
                    case 15:
                        atualizarRitual(scanner, ritualDAO, deusesDAO);
                        break;
                    case 16:
                        deletarRitual(scanner, ritualDAO);
                        break;
                    case 17:
                        cadastrarEsferaPoder(scanner, esferaPoderDAO);
                        break;
                    case 18:
                        listarEsferasPoder(esferaPoderDAO);
                        break;
                    case 19:
                        atualizarEsferaPoder(scanner, esferaPoderDAO);
                        break;
                    case 20:
                        deletarEsferaPoder(scanner, esferaPoderDAO);
                        break;
                    case 0:
                        System.out.println("Saindo do programa. Adeus!");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                choice = -1;
            } catch (SQLException e) {
                System.err.println("Erro de banco de dados: " + e.getMessage());
                e.printStackTrace();
            }
        } while (choice != 0);

        scanner.close();
    }

    /**
     * Permite ao usuário cadastrar um novo Deus.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param deusesDAO DAO para operações com Deuses.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void cadastrarDeus(Scanner scanner, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Adicionar Novo Deus ---");
        System.out.print("Nome do Deus: ");
        String nome = scanner.nextLine();
        System.out.print("Domínio: ");
        String dominio = scanner.nextLine();
        System.out.print("Era Mitológica: ");
        String eraMitologica = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Deuses novoDeus = new Deuses(nome, dominio, eraMitologica, descricao);
        deusesDAO.create(novoDeus);
        System.out.println("Deus '" + novoDeus.getNome() + "' criado com ID: " + novoDeus.getId());
    }

    /**
     * Lista todos os Deuses cadastrados.
     * @param deusesDAO DAO para operações com Deuses.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void listarDeuses(DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Lista de Deuses ---");
        List<Deuses> deuses = deusesDAO.findAll();
        if (deuses.isEmpty()) {
            System.out.println("Nenhum deus cadastrado.");
        } else {
            deuses.forEach(System.out::println);
        }
    }

    /**
     * Permite ao usuário atualizar um Deus existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param deusesDAO DAO para operações com Deuses.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void atualizarDeus(Scanner scanner, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Atualizar Deus ---");
        System.out.print("ID do Deus a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Deuses deusExistente = deusesDAO.findById(id);
        if (deusExistente == null) {
            System.out.println("Deus com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Deus atual: " + deusExistente);
        System.out.print("Novo Nome (deixe em branco para manter '" + deusExistente.getNome() + "'): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            deusExistente.setNome(nome);
        }

        System.out.print("Novo Domínio (deixe em branco para manter '" + deusExistente.getDominio() + "'): ");
        String dominio = scanner.nextLine();
        if (!dominio.isEmpty()) {
            deusExistente.setDominio(dominio);
        }

        System.out.print("Nova Era Mitológica (deixe em branco para manter '" + deusExistente.getEraMitologica() + "'): ");
        String eraMitologica = scanner.nextLine();
        if (!eraMitologica.isEmpty()) {
            deusExistente.setEraMitologica(eraMitologica);
        }

        System.out.print("Nova Descrição (deixe em branco para manter '" + deusExistente.getDescricao() + "'): ");
        String descricao = scanner.nextLine();
        if (!descricao.isEmpty()) {
            deusExistente.setDescricao(descricao);
        }

        deusesDAO.update(deusExistente);
        System.out.println("Deus atualizado com sucesso!");
    }

    /**
     * Permite ao usuário deletar um Deus existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param deusesDAO DAO para operações com Deuses.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void deletarDeus(Scanner scanner, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Deletar Deus ---");
        System.out.print("ID do Deus a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Deuses deusExistente = deusesDAO.findById(id);
        if (deusExistente == null) {
            System.out.println("Deus com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Você tem certeza que deseja deletar o Deus: " + deusExistente.getNome() + " (ID: " + id + ")? (sim/nao)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("sim")) {
            deusesDAO.delete(id);
            System.out.println("Deus deletado com sucesso!");
        } else {
            System.out.println("Operação de deleção cancelada.");
        }
    }

    /**
     * Permite ao usuário cadastrar um novo Seguidor.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param seguidorDAO DAO para operações com Seguidores.
     * @param deusesDAO DAO para listar Deuses (para associação).
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void cadastrarSeguidor(Scanner scanner, SeguidorDAO seguidorDAO, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Adicionar Novo Seguidor ---");
        System.out.print("Nome do Seguidor: ");
        String nome = scanner.nextLine();

        listarDeuses(deusesDAO);
        System.out.print("ID do Deus ao qual este seguidor pertence (0 para nenhum): ");
        Integer idDeus = scanner.nextInt();
        scanner.nextLine();

        if (idDeus != 0 && deusesDAO.findById(idDeus) == null) {
            System.out.println("Deus com ID " + idDeus + " não encontrado. Seguidor será criado sem associação a um Deus.");
            idDeus = null;
        } else if (idDeus == 0) {
            idDeus = null;
        }


        System.out.print("Tipo de Seguidor (ex: Cavaleiro de Bronze, Espectro): ");
        String tipoSeguidor = scanner.nextLine();
        System.out.print("Patente (ex: Pégaso, Juiz): ");
        String patente = scanner.nextLine();
        System.out.print("Constelação Marinha/Estrela: ");
        String constelacaoMarinhaEstrela = scanner.nextLine();

        Seguidor novoSeguidor = new Seguidor(nome, idDeus, tipoSeguidor, patente, constelacaoMarinhaEstrela);
        seguidorDAO.create(novoSeguidor);
        System.out.println("Seguidor '" + novoSeguidor.getNome() + "' criado com ID: " + novoSeguidor.getId());
    }

    /**
     * Lista todos os Seguidores cadastrados.
     * @param seguidorDAO DAO para operações com Seguidores.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void listarSeguidores(SeguidorDAO seguidorDAO) throws SQLException {
        System.out.println("\n--- Lista de Seguidores ---");
        List<Seguidor> seguidores = seguidorDAO.findAll();
        if (seguidores.isEmpty()) {
            System.out.println("Nenhum seguidor cadastrado.");
        } else {
            seguidores.forEach(System.out::println);
        }
    }

    /**
     * Permite ao usuário atualizar um Seguidor existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param seguidorDAO DAO para operações com Seguidores.
     * @param deusesDAO DAO para listar Deuses (para associação).
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void atualizarSeguidor(Scanner scanner, SeguidorDAO seguidorDAO, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Atualizar Seguidor ---");
        System.out.print("ID do Seguidor a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Seguidor seguidorExistente = seguidorDAO.findById(id);
        if (seguidorExistente == null) {
            System.out.println("Seguidor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Seguidor atual: " + seguidorExistente);
        System.out.print("Novo Nome (deixe em branco para manter '" + seguidorExistente.getNome() + "'): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            seguidorExistente.setNome(nome);
        }

        listarDeuses(deusesDAO);
        System.out.print("Novo ID do Deus (0 para nenhum, deixe em branco para manter " + (seguidorExistente.getIdDeus() == null ? "nenhum" : seguidorExistente.getIdDeus()) + "): ");
        String idDeusStr = scanner.nextLine();
        if (!idDeusStr.isEmpty()) {
            Integer novoIdDeus = Integer.parseInt(idDeusStr);
            if (novoIdDeus != 0 && deusesDAO.findById(novoIdDeus) == null) {
                System.out.println("Deus com ID " + novoIdDeus + " não encontrado. Mantendo a associação atual.");
            } else if (novoIdDeus == 0) {
                seguidorExistente.setIdDeus(null);
            } else {
                seguidorExistente.setIdDeus(novoIdDeus);
            }
        }


        System.out.print("Novo Tipo de Seguidor (deixe em branco para manter '" + seguidorExistente.getTipoSeguidor() + "'): ");
        String tipoSeguidor = scanner.nextLine();
        if (!tipoSeguidor.isEmpty()) {
            seguidorExistente.setTipoSeguidor(tipoSeguidor);
        }

        System.out.print("Nova Patente (deixe em branco para manter '" + seguidorExistente.getPatente() + "'): ");
        String patente = scanner.nextLine();
        if (!patente.isEmpty()) {
            seguidorExistente.setPatente(patente);
        }

        System.out.print("Nova Constelação Marinha/Estrela (deixe em branco para manter '" + seguidorExistente.getConstelacaoMarinhaEstrela() + "'): ");
        String constelacaoMarinhaEstrela = scanner.nextLine();
        if (!constelacaoMarinhaEstrela.isEmpty()) {
            seguidorExistente.setConstelacaoMarinhaEstrela(constelacaoMarinhaEstrela);
        }

        seguidorDAO.update(seguidorExistente);
        System.out.println("Seguidor atualizado com sucesso!");
    }

    /**
     * Permite ao usuário deletar um Seguidor existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param seguidorDAO DAO para operações com Seguidores.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void deletarSeguidor(Scanner scanner, SeguidorDAO seguidorDAO) throws SQLException {
        System.out.println("\n--- Deletar Seguidor ---");
        System.out.print("ID do Seguidor a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Seguidor seguidorExistente = seguidorDAO.findById(id);
        if (seguidorExistente == null) {
            System.out.println("Seguidor com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Você tem certeza que deseja deletar o Seguidor: " + seguidorExistente.getNome() + " (ID: " + id + ")? (sim/nao)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("sim")) {
            seguidorDAO.delete(id);
            System.out.println("Seguidor deletado com sucesso!");
        } else {
            System.out.println("Operação de deleção cancelada.");
        }
    }

    /**
     * Permite ao usuário cadastrar um novo Templo.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param temploDAO DAO para operações com Templos.
     * @param deusesDAO DAO para listar Deuses (para associação).
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void cadastrarTemplo(Scanner scanner, TemploDAO temploDAO, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Adicionar Novo Templo ---");
        System.out.print("Nome do Templo: ");
        String nomeTemplo = scanner.nextLine();

        listarDeuses(deusesDAO);
        System.out.print("ID do Deus associado a este Templo (0 para nenhum): ");
        Integer idDeus = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha

        if (idDeus != 0 && deusesDAO.findById(idDeus) == null) {
            System.out.println("Deus com ID " + idDeus + " não encontrado. Templo será criado sem associação a um Deus.");
            idDeus = null;
        } else if (idDeus == 0) {
            idDeus = null;
        }

        System.out.print("Localização: ");
        String localizacao = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        Templo novoTemplo = new Templo(nomeTemplo, idDeus, localizacao, descricao);
        temploDAO.create(novoTemplo);
        System.out.println("Templo '" + novoTemplo.getNomeTemplo() + "' criado com ID: " + novoTemplo.getId());
    }

    /**
     * Lista todos os Templos cadastrados.
     * @param temploDAO DAO para operações com Templos.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void listarTemplos(TemploDAO temploDAO) throws SQLException {
        System.out.println("\n--- Lista de Templos ---");
        List<Templo> templos = temploDAO.findAll();
        if (templos.isEmpty()) {
            System.out.println("Nenhum templo cadastrado.");
        } else {
            templos.forEach(System.out::println);
        }
    }

    /**
     * Permite ao usuário atualizar um Templo existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param temploDAO DAO para operações com Templos.
     * @param deusesDAO DAO para listar Deuses (para associação).
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void atualizarTemplo(Scanner scanner, TemploDAO temploDAO, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Atualizar Templo ---");
        System.out.print("ID do Templo a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        Templo temploExistente = temploDAO.findById(id);
        if (temploExistente == null) {
            System.out.println("Templo com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Templo atual: " + temploExistente);
        System.out.print("Novo Nome do Templo (deixe em branco para manter '" + temploExistente.getNomeTemplo() + "'): ");
        String nomeTemplo = scanner.nextLine();
        if (!nomeTemplo.isEmpty()) {
            temploExistente.setNomeTemplo(nomeTemplo);
        }

        listarDeuses(deusesDAO);
        System.out.print("Novo ID do Deus (0 para nenhum, deixe em branco para manter " + (temploExistente.getIdDeus() == null ? "nenhum" : temploExistente.getIdDeus()) + "): ");
        String idDeusStr = scanner.nextLine();
        if (!idDeusStr.isEmpty()) {
            Integer novoIdDeus = Integer.parseInt(idDeusStr);
            if (novoIdDeus != 0 && deusesDAO.findById(novoIdDeus) == null) {
                System.out.println("Deus com ID " + novoIdDeus + " não encontrado. Mantendo a associação atual.");
            } else if (novoIdDeus == 0) {
                temploExistente.setIdDeus(null);
            } else {
                temploExistente.setIdDeus(novoIdDeus);
            }
        }

        System.out.print("Nova Localização (deixe em branco para manter '" + temploExistente.getLocalizacao() + "'): ");
        String localizacao = scanner.nextLine();
        if (!localizacao.isEmpty()) {
            temploExistente.setLocalizacao(localizacao);
        }

        System.out.print("Nova Descrição (deixe em branco para manter '" + temploExistente.getDescricao() + "'): ");
        String descricao = scanner.nextLine();
        if (!descricao.isEmpty()) {
            temploExistente.setDescricao(descricao);
        }

        temploDAO.update(temploExistente);
        System.out.println("Templo atualizado com sucesso!");
    }

    /**
     * Permite ao usuário deletar um Templo existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param temploDAO DAO para operações com Templos.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void deletarTemplo(Scanner scanner, TemploDAO temploDAO) throws SQLException {
        System.out.println("\n--- Deletar Templo ---");
        System.out.print("ID do Templo a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Templo temploExistente = temploDAO.findById(id);
        if (temploExistente == null) {
            System.out.println("Templo com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Você tem certeza que deseja deletar o Templo: " + temploExistente.getNomeTemplo() + " (ID: " + id + ")? (sim/nao)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("sim")) {
            temploDAO.delete(id);
            System.out.println("Templo deletado com sucesso!");
        } else {
            System.out.println("Operação de deleção cancelada.");
        }
    }


    /**
     * Permite ao usuário cadastrar um novo Ritual.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param ritualDAO DAO para operações com Rituais.
     * @param deusesDAO DAO para listar Deuses (para associação).
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void cadastrarRitual(Scanner scanner, RitualDAO ritualDAO, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Adicionar Novo Ritual ---");
        System.out.print("Nome do Ritual: ");
        String nomeRitual = scanner.nextLine();

        listarDeuses(deusesDAO);
        System.out.print("ID do Deus associado a este Ritual (0 para nenhum): ");
        Integer idDeus = scanner.nextInt();
        scanner.nextLine();

        if (idDeus != 0 && deusesDAO.findById(idDeus) == null) {
            System.out.println("Deus com ID " + idDeus + " não encontrado. Ritual será criado sem associação a um Deus.");
            idDeus = null;
        } else if (idDeus == 0) {
            idDeus = null;
        }

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Requisitos: ");
        String requisitos = scanner.nextLine();

        Ritual novoRitual = new Ritual(nomeRitual, idDeus, descricao, requisitos);
        ritualDAO.create(novoRitual);
        System.out.println("Ritual '" + novoRitual.getNomeRitual() + "' criado com ID: " + novoRitual.getId());
    }

    /**
     * Lista todos os Rituais cadastrados.
     * @param ritualDAO DAO para operações com Rituais.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void listarRituais(RitualDAO ritualDAO) throws SQLException {
        System.out.println("\n--- Lista de Rituais ---");
        List<Ritual> rituais = ritualDAO.findAll();
        if (rituais.isEmpty()) {
            System.out.println("Nenhum ritual cadastrado.");
        } else {
            rituais.forEach(System.out::println);
        }
    }

    /**
     * Permite ao usuário atualizar um Ritual existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param ritualDAO DAO para operações com Rituais.
     * @param deusesDAO DAO para listar Deuses (para associação).
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void atualizarRitual(Scanner scanner, RitualDAO ritualDAO, DeusesDAO deusesDAO) throws SQLException {
        System.out.println("\n--- Atualizar Ritual ---");
        System.out.print("ID do Ritual a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Ritual ritualExistente = ritualDAO.findById(id);
        if (ritualExistente == null) {
            System.out.println("Ritual com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Ritual atual: " + ritualExistente);
        System.out.print("Novo Nome do Ritual (deixe em branco para manter '" + ritualExistente.getNomeRitual() + "'): ");
        String nomeRitual = scanner.nextLine();
        if (!nomeRitual.isEmpty()) {
            ritualExistente.setNomeRitual(nomeRitual);
        }

        listarDeuses(deusesDAO);
        System.out.print("Novo ID do Deus (0 para nenhum, deixe em branco para manter " + (ritualExistente.getIdDeus() == null ? "nenhum" : ritualExistente.getIdDeus()) + "): ");
        String idDeusStr = scanner.nextLine();
        if (!idDeusStr.isEmpty()) {
            Integer novoIdDeus = Integer.parseInt(idDeusStr);
            if (novoIdDeus != 0 && deusesDAO.findById(novoIdDeus) == null) {
                System.out.println("Deus com ID " + novoIdDeus + " não encontrado. Mantendo a associação atual.");
            } else if (novoIdDeus == 0) {
                ritualExistente.setIdDeus(null);
            } else {
                ritualExistente.setIdDeus(novoIdDeus);
            }
        }

        System.out.print("Nova Descrição (deixe em branco para manter '" + ritualExistente.getDescricao() + "'): ");
        String descricao = scanner.nextLine();
        if (!descricao.isEmpty()) {
            ritualExistente.setDescricao(descricao);
        }

        System.out.print("Novos Requisitos (deixe em branco para manter '" + ritualExistente.getRequisitos() + "'): ");
        String requisitos = scanner.nextLine();
        if (!requisitos.isEmpty()) {
            ritualExistente.setRequisitos(requisitos);
        }

        ritualDAO.update(ritualExistente);
        System.out.println("Ritual atualizado com sucesso!");
    }

    /**
     * Permite ao usuário deletar um Ritual existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param ritualDAO DAO para operações com Rituais.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void deletarRitual(Scanner scanner, RitualDAO ritualDAO) throws SQLException {
        System.out.println("\n--- Deletar Ritual ---");
        System.out.print("ID do Ritual a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Ritual ritualExistente = ritualDAO.findById(id);
        if (ritualExistente == null) {
            System.out.println("Ritual com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Você tem certeza que deseja deletar o Ritual: " + ritualExistente.getNomeRitual() + " (ID: " + id + ")? (sim/nao)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("sim")) {
            ritualDAO.delete(id);
            System.out.println("Ritual deletado com sucesso!");
        } else {
            System.out.println("Operação de deleção cancelada.");
        }
    }

    /**
     * Permite ao usuário cadastrar uma nova Esfera de Poder.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param esferaPoderDAO DAO para operações com Esferas de Poder.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void cadastrarEsferaPoder(Scanner scanner, EsferaPoderDAO esferaPoderDAO) throws SQLException {
        System.out.println("\n--- Adicionar Nova Esfera de Poder ---");
        System.out.print("Nome da Esfera: ");
        String nomeEsfera = scanner.nextLine();
        System.out.print("Tipo de Energia: ");
        String tipoEnergia = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        EsferaPoder novaEsfera = new EsferaPoder(nomeEsfera, tipoEnergia, descricao);
        esferaPoderDAO.create(novaEsfera);
        System.out.println("Esfera de Poder '" + novaEsfera.getNomeEsfera() + "' criada com ID: " + novaEsfera.getId());
    }

    /**
     * Lista todas as Esferas de Poder cadastradas.
     * @param esferaPoderDAO DAO para operações com Esferas de Poder.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void listarEsferasPoder(EsferaPoderDAO esferaPoderDAO) throws SQLException {
        System.out.println("\n--- Lista de Esferas de Poder ---");
        List<EsferaPoder> esferas = esferaPoderDAO.findAll();
        if (esferas.isEmpty()) {
            System.out.println("Nenhuma esfera de poder cadastrada.");
        } else {
            esferas.forEach(System.out::println);
        }
    }

    /**
     * Permite ao usuário atualizar uma Esfera de Poder existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param esferaPoderDAO DAO para operações com Esferas de Poder.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void atualizarEsferaPoder(Scanner scanner, EsferaPoderDAO esferaPoderDAO) throws SQLException {
        System.out.println("\n--- Atualizar Esfera de Poder ---");
        System.out.print("ID da Esfera de Poder a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        EsferaPoder esferaExistente = esferaPoderDAO.findById(id);
        if (esferaExistente == null) {
            System.out.println("Esfera de Poder com ID " + id + " não encontrada.");
            return;
        }

        System.out.println("Esfera de Poder atual: " + esferaExistente);
        System.out.print("Novo Nome da Esfera (deixe em branco para manter '" + esferaExistente.getNomeEsfera() + "'): ");
        String nomeEsfera = scanner.nextLine();
        if (!nomeEsfera.isEmpty()) {
            esferaExistente.setNomeEsfera(nomeEsfera);
        }

        System.out.print("Novo Tipo de Energia (deixe em branco para manter '" + esferaExistente.TipoEnergia() + "'): ");
        String tipoEnergia = scanner.nextLine();
        if (!tipoEnergia.isEmpty()) {
            esferaExistente.setTipoEnergia(tipoEnergia);
        }

        System.out.print("Nova Descrição (deixe em branco para manter '" + esferaExistente.getDescricao() + "'): ");
        String descricao = scanner.nextLine();
        if (!descricao.isEmpty()) {
            esferaExistente.setDescricao(descricao);
        }

        esferaPoderDAO.update(esferaExistente);
        System.out.println("Esfera de Poder atualizada com sucesso!");
    }

    /**
     * Permite ao usuário deletar uma Esfera de Poder existente.
     * @param scanner Objeto Scanner para leitura de entrada.
     * @param esferaPoderDAO DAO para operações com Esferas de Poder.
     * @throws SQLException Se ocorrer um erro de banco de dados.
     */
    private static void deletarEsferaPoder(Scanner scanner, EsferaPoderDAO esferaPoderDAO) throws SQLException {
        System.out.println("\n--- Deletar Esfera de Poder ---");
        System.out.print("ID da Esfera de Poder a ser deletada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        EsferaPoder esferaExistente = esferaPoderDAO.findById(id);
        if (esferaExistente == null) {
            System.out.println("Esfera de Poder com ID " + id + " não encontrada.");
            return;
        }

        System.out.println("Você tem certeza que deseja deletar a Esfera de Poder: " + esferaExistente.getNomeEsfera() + " (ID: " + id + ")? (sim/nao)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("sim")) {
            esferaPoderDAO.delete(id);
            System.out.println("Esfera de Poder deletada com sucesso!");
        } else {
            System.out.println("Operação de deleção cancelada.");
        }
    }
}