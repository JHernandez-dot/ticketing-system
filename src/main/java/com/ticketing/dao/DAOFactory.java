package com.ticketing.dao;

public class DAOFactory {
    private static DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private static RolDAO rolDAO = new RolDAO();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static TicketDAO ticketDAO = new TicketDAO();
    private static NotaDAO notaDAO = new NotaDAO();

    public static DepartamentoDAO getDepartamentoDAO() {
        return departamentoDAO;
    }

    public static RolDAO getRolDAO() {
        return rolDAO;
    }

    public static UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public static TicketDAO getTicketDAO() {
        return ticketDAO;
    }

    public static NotaDAO getNotaDAO() {
        return notaDAO;
    }
}
