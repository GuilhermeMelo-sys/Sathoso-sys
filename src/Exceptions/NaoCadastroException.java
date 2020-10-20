/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Guilh
 */
public class NaoCadastroException extends RuntimeException{
    
    public NaoCadastroException(String msg){
        super(msg);
    }
    
    public NaoCadastroException(String msg, Throwable cause){
        super(msg, cause);
    }
}
