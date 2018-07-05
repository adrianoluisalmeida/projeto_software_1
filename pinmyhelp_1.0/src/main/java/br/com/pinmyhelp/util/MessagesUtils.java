/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.util;


import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.Message;
import br.com.pinmyhelp.model.User;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author rhau
 */
public class MessagesUtils {
    
    public static final String TITLE_OFFER_APPROVED = "Oferta aprovada"; // to: Voluntary
    public static final String TITLE_OFFER_CLOSED = "Solicitação finalizada"; // to: Voluntary
    public static final String TITLE_OFFER_REJECTED = "Oferta não aceita"; // to: Voluntary
    public static final String TITLE_SOLICITATION_DELETED = "Solicitação cancelada";  // to: Voluntary
    
    public static final String TITLE_OFFER_RECEIVED = "Oferta de ajuda recebida"; // to: Claimant
    public static final String TITLE_OFFER_DELETED = "Oferta cancelada"; // to Claimant
    
    private static final String END = "Agradecemos sua disposição e esperamos que continue ajudando com o PinMyHelp! :)";
    
    public static String offerApproved(HelpSolicitation help) {
        return "A sua oferta de ajuda para '" + help.getType().getType() + "' com " + getReceiver(help) 
                + " foi aprovada. A data de início está marcada para dia " + FormatUtils.toString(Date.valueOf(help.getStartDate()), "dd/MM/yyyy")
                + ". Veja mais em 'Minhas Ofertas'.<br/>" + END;
    }     
    
    public static String offerClosed(HelpSolicitation help) {
        return "A solicitação de ajuda para '" + help.getType().getType() + "' com " + getReceiver(help)
                + " já foi finalizada, outro voluntário se ofereceu para a tarefa e teve a"
                + " oferta aceita.<br/>" + END;
    }
   
    public static String offerRejected(HelpSolicitation help, String cause) {
        return "Sua oferta de ajuda para '" + help.getType().getType() + "' com " + getReceiver(help)
                + " acabou não sendo aprovada. Motivo segundo o requerente: \"" + cause 
                + "\"<br/> " + END;
    }
    
    public static String solicitationDeleted(HelpSolicitation help) {
        return "A solicitação de ajuda para '" + help.getType().getType() + "' com " + getReceiver(help)
                + " foi cancelada pelo requerente.<br/>" + END;  
    }
    
    public static String offerReceived(HelpSolicitation help, HelpOffer offer) {
        return "Você recebeu uma oferta de ajuda para '" + help.getType().getType() + "' " + getReceiver(offer)
                + ". Confira em 'Todas ofertas.";        
    }
    
    private static String offerDeleted(HelpSolicitation help, HelpOffer offer) {
        return "Você recebeu uma oferta de ajuda para '" + help.getType().getType() + "' " + getReceiver(offer)
                + " foi cancelada pelo mesmo.";          
    }
    
    public static Message createMessageApproved(HelpSolicitation help, HelpOffer offer) {
        return createMessage(offer, TITLE_OFFER_APPROVED, offerApproved(help));

    }
    public static Message createMessageClosed(HelpSolicitation help, HelpOffer offer) {
        return createMessage(offer, TITLE_OFFER_CLOSED, offerClosed(help));
    }
    
    public static Message createMessageRejected(HelpSolicitation help, HelpOffer offer, String cause) {
        return createMessage(offer, TITLE_OFFER_REJECTED, offerRejected(help, cause));
    }
    
    public static Message createMessageReceived(HelpSolicitation help, HelpOffer offer) {
        return createMessage(help, TITLE_OFFER_RECEIVED, offerReceived(help, offer));
    }
    
    public static Message createMessageDeleted(HelpSolicitation help, HelpOffer offer) {
        return createMessage(help, TITLE_SOLICITATION_DELETED, solicitationDeleted(help));
    }
    
    private static Message createMessage(HelpOffer offer, String title, String content) {
        Message m = new Message();
        m.setTitle(title);
        m.setContent(content);
        if (offer.getEntity() != null)
            m.setUser(new User(offer.getEntity().getId()));
        else
            m.setUser(new User(offer.getVoluntary().getId()));
        return m;
    }
    
    private static Message createMessage(HelpSolicitation help, String title, String content) {
        Message m = new Message();
        m.setTitle(title);
        m.setContent(content);
        if (help.getEntity() != null)
            m.setUser(new User(help.getEntity().getId()));
        else
            m.setUser(new User(help.getClaimant().getId()));
        return m;
    }

    private static String getReceiver(HelpSolicitation help) {
        return help.getClaimant() != null ? "o requerente " + help.getClaimant().getName() :
                                            "a entidade " + help.getEntity().getName();
    }
    
    private static String getReceiver(HelpOffer offer) {
        return offer.getVoluntary() != null ? "do voluntário " + offer.getVoluntary().getName() :
                                              "da entidade " + offer.getEntity().getName();
    }
    
}