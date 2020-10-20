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
public class CadastradoException extends Exception{
    public CadastradoException(String msg){
        super(msg);
    }
    
    public CadastradoException(String msg, Throwable cause){
        super(msg, cause);
    }
}
