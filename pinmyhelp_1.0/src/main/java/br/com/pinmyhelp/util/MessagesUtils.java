/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.util;


import br.com.pinmyhelp.model.HelpOffer;
import br.com.pinmyhelp.model.HelpSolicitation;
import br.com.pinmyhelp.model.types.HelpType;

/**
 *
 * @author rhau
 */
public class MessageUtils {
    
    public static final String TITLE_OFFER_APPROVED = "Ajuda aprovada";
    public static final String TITLE_OFFER_CLOSED = "Solicitação finalizada";
    public static final String TITLE_OFFER_REJECTED = "Oferta não aceita";
    public static final String TITLE_OFFER_DELETED = "Solicitação cancelada";    
    public static final String TITLE_OFFER_RECEIVED = "Oferta de ajuda recebida";
    
    private static final String END = "Agradecemos sua disposição e esperamos que continue ajudando com o PinMyHelp! :)";
    
    public static String offerApproved(HelpSolicitation help, HelpType type) {
        return "A oferta de ajuda para '" + type.name() + "' com " + getReceiver(help) 
                + " foi aprovada. A data de início está marcada para dia " + help.getStartDate()
                + " em " + help.getAddress().getCity() + ", no bairro " + help.getAddress().getNeighborhood()
                + ", rua " + help.getAddress().getStreet() + " Nº " + help.getAddress().getNumber()
                + ". Veja mais em 'Minhas Ofertas'. \n" + END;
    }     
    
    public static String offerClosed(HelpSolicitation help, HelpType type) {
        return "A solicitção de ajuda para '" + type.name() + "' com " + getReceiver(help)
                + " já foi finalizada, outro voluntário se ofereceu para a tarefa.\n" + END;
    }
   
    public static String offerRejected(HelpSolicitation help, HelpType type, String cause) {
        return "Sua oferta de ajuda para '" + type.name() + "' com " + getReceiver(help)
                + " acabou não sendo aprovada. Motivo segundo o requerente: '" + cause 
                + "\n " + END;
    }
    
    public static String offerDeleted(HelpSolicitation help, HelpType type) {
        return "<escrever>";
    }
    
    public static String offerReceived(HelpSolicitation help, HelpType type, HelpOffer offer) {
        return "<escrever>";        
    }
    
    private static String getReceiver(HelpSolicitation help) {
        return help.getClaimant() != null ? "o requerente " + help.getClaimant().getName() :
                                            "a entidade " + help.getEntity().getName();
    }
    
}