/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.dao;

import com.optmant.dao.Connection;

;

/**
 *
 * @author Colada
 */
public class BaseDAO {

    protected Connection connectionManager;

    public BaseDAO(Connection connectionManager) {
        this.connectionManager = connectionManager;
    }
}
