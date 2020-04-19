package com.vinireis.renomeararquivos.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class RenameFile {
	/**
	 * target - palavra a ser substituida
	 * source - valor que vai substituir o target
	 */
	private String target, source, local = null;
	private ArrayList<String> lista = new ArrayList<String>();

	public RenameFile(String[] args) {
		target = args[0];
		source = args[1];
		local = args[2];
		
		System.out.println("Target: " + target);
		System.out.println("Source: " + source);
		System.out.println("Local : " + local + "\n\n\n\n");
		
		readFiles();
		renameFiles();
	}

	private void renameFiles() {
		for(String aux : lista){
			try {
				File oldFile = new File(aux);			
				File newFile = new File(oldFile.getParent() , oldFile.getName().replaceAll(target, source));
				newFile.createNewFile();  
		        Writer arquivo = new FileWriter(newFile);  
		        if (oldFile.exists()){
		        	System.out.println("Old name: " + oldFile.getName() + "\nNew name: " + newFile.getName());
		        	System.out.println(oldFile.renameTo(newFile) + " Existe? " + newFile.exists() + "\n" +
		        			"nome: " + newFile.getAbsoluteFile() + " \n");
		        	oldFile.deleteOnExit();		        	
		        }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public RenameFile(String target, String source, String local) {
		
		this.target = target;
		this.source = source;
		this.local = local;
	}

	/**
	 * @param args
	 * args[0] - target to be replaced
	 * args[1] - source for new value
	 * args[2] - local where the replace name file will be executed
	 */
	public static void main(String[] args) {
		
		new RenameFile(args);
	}

	public void readFiles() {
		
		if (local == null || local.equalsIgnoreCase("")){
			System.err.println("Valor inválido para o parâmetro #3 - Local!!!");
			return;
		}
		
		if (source == null || source.equalsIgnoreCase("")){
			System.err.println("Valor inválido para o parâmetro #2 - Source!!!");
			return;
		}
		
		if (target == null || target.equalsIgnoreCase("")){
			System.err.println("Valor inválido para o parâmetro #1 - Target!!!");
			return;
		}
		
		File local = new File(this.local);
		
		if (!local.exists()){
			System.err.println("O diretório especificado no argumento #3 não existe!!!");
			return;
		}
		
		for(File file : local.listFiles()){
		
			if (file.getAbsolutePath().contains(target)){
				lista.add(file.getAbsolutePath());
			}
		}	
		
	}
}