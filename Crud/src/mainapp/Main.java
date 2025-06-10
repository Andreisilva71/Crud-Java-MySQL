package mainapp;

import DAO.*;
import entity.*;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DeusesDAO deusesDAO = new DeusesDAO();
        SeguidorDAO seguidorDAO = new SeguidorDAO();
        TemploDAO temploDAO = new TemploDAO();
        RitualDAO ritualDAO = new RitualDAO();
        EsferaPoderDAO esferaPoderDAO = new EsferaPoderDAO();

        Deuses atena = null;
        Deuses hades = null;
        Deuses atenaRecuperada = null;
        Seguidor seiya = null;
        Seguidor saga = null;
        Seguidor radamanthys = null;
        Templo santuario = null;
        Templo casteloHades = null;
        Ritual exclamacaoAtena = null;
        Ritual punicaoDivina = null;
        EsferaPoder cosmo = null;
        EsferaPoder arayaShiki = null;

        try {
            System.out.println("--- CRUD Deuses ---");
            // Criar Deus
            atena = new Deuses("Atena", "Sabedoria e Guerra Estratégica", "Grega", "Deusa da sabedoria, guerra e justiça.");
            deusesDAO.create(atena);
            System.out.println("Deus criado: " + atena);

            hades = new Deuses("Hades", "Mundo dos Mortos", "Grega", "Deus do submundo e da riqueza.");
            deusesDAO.create(hades);
            System.out.println("Deus criado: " + hades);

            List<Deuses> deuses = deusesDAO.findAll();
            System.out.println("\nTodos os Deuses:");
            deuses.forEach(System.out::println);

            atenaRecuperada = deusesDAO.findById(atena.getId());
            System.out.println("\nDeus recuperado (Atena): " + atenaRecuperada);

            if (atenaRecuperada != null) {
                atenaRecuperada.setDominio("Sabedoria, Guerra e Paz");
                deusesDAO.update(atenaRecuperada);
                System.out.println("Deus atualizado (Atena): " + deusesDAO.findById(atena.getId()));
            }

            System.out.println("\n--- CRUD Seguidores ---");
            seiya = new Seguidor("Seiya de Pégaso", atena.getId(), "Cavaleiro de Bronze", "Pégaso", "Constelação de Pégaso");
            seguidorDAO.create(seiya);
            System.out.println("Seguidor criado: " + seiya);

            saga = new Seguidor("Saga de Gêmeos", atena.getId(), "Cavaleiro de Ouro", "Gêmeos", "Constelação de Gêmeos");
            seguidorDAO.create(saga);
            System.out.println("Seguidor criado: " + saga);

            radamanthys = new Seguidor("Radamanthys de Wyvern", hades.getId(), "Espectro", "Juiz", "Estrela Celeste da Ferocidade");
            seguidorDAO.create(radamanthys);
            System.out.println("Seguidor criado: " + radamanthys);

            // Listar Seguidores
            List<Seguidor> seguidores = seguidorDAO.findAll();
            System.out.println("\nTodos os Seguidores:");
            seguidores.forEach(System.out::println);

            // --- CRUD para Templos ---
            System.out.println("\n--- CRUD Templos ---");
            // Criar Templo
            santuario = new Templo("Santuário de Atena", atena.getId(), "Grécia", "Local sagrado de Atena e seus cavaleiros.");
            temploDAO.create(santuario);
            System.out.println("Templo criado: " + santuario);

            casteloHades = new Templo("Castelo de Hades", hades.getId(), "Eliseos", "Fortaleza principal de Hades no submundo.");
            temploDAO.create(casteloHades);
            System.out.println("Templo criado: " + casteloHades);

            // Listar Templos
            List<Templo> templos = temploDAO.findAll();
            System.out.println("\nTodos os Templos:");
            templos.forEach(System.out::println);

            // --- CRUD para Rituais ---
            System.out.println("\n--- CRUD Rituais ---");
            // Criar Ritual
            exclamacaoAtena = new Ritual("Exclamação de Atena", atena.getId(), "Ataque combinado de três cavaleiros de ouro.", "3 Cavaleiros de Ouro.");
            ritualDAO.create(exclamacaoAtena);
            System.out.println("Ritual criado: " + exclamacaoAtena);

            punicaoDivina = new Ritual("Punição Divina", hades.getId(), "Ritual para selar almas no Cocytus.", "Hades.");
            ritualDAO.create(punicaoDivina);
            System.out.println("Ritual criado: " + punicaoDivina);

            // Listar Rituais
            List<Ritual> rituais = ritualDAO.findAll();
            System.out.println("\nTodos os Rituais:");
            rituais.forEach(System.out::println);

            System.out.println("\n--- CRUD Esferas de Poder ---");
            cosmo = new EsferaPoder("Cosmo", "Energia Espiritual", "A essência da vida e poder nos Cavaleiros.");
            esferaPoderDAO.create(cosmo);
            System.out.println("Esfera de Poder criada: " + cosmo);

            arayaShiki = new EsferaPoder("Arayashiki", "Oitavo Sentido", "Um estado de consciência que transcende a morte.");
            esferaPoderDAO.create(arayaShiki);
            System.out.println("Esfera de Poder criada: " + arayaShiki);

            // Listar Esferas de Poder
            List<EsferaPoder> esferas = esferaPoderDAO.findAll();
            System.out.println("\nTodas as Esferas de Poder:");
            esferas.forEach(System.out::println);

            // --- Exemplo de Deleção ---
            System.out.println("\n--- Exemplo de Deleção ---");
            // Agora 'exclamacaoAtena' e 'arayashiki' estão acessíveis aqui
            if (exclamacaoAtena != null) {
                ritualDAO.delete(exclamacaoAtena.getId());
                System.out.println("Ritual Exclamação de Atena deletado.");
            }
            if (arayaShiki != null) { // arayashiki está acessível agora
                esferaPoderDAO.delete(arayaShiki.getId()); // arayashiki está acessível agora
                System.out.println("Esfera de Poder Arayashiki deletada.");
            }

            // Verifique novamente as listas
            System.out.println("\nTodos os Rituais após deleção:");
            ritualDAO.findAll().forEach(System.out::println);
            System.out.println("\nTodas as Esferas de Poder após deleção:");
            esferaPoderDAO.findAll().forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Erro de banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}