/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Interfaces;

/**
 *
 * @author Guilh
 * @param <T>
 */
public interface ReadOnly<T> {
    @Override
    String toString();
    
    @Override
    boolean equals(Object t);
}
