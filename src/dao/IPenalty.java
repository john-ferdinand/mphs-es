/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.penalty.Penalty;

/**
 *
 * @author Jordan
 */
public interface IPenalty {
    boolean hasExistingPenaltyFor(String balancebreakdownName, int schoolYearId, int registrationId);
    List<Penalty> getPenaltyBy(int orNo);
}
