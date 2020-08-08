/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author Threading
 */
public interface Operations {
 int save(student o);
 int update(student o);
 int delete(int o);
 int login(student o);
}
