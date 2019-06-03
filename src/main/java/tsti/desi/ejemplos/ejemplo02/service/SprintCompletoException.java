package tsti.desi.ejemplos.ejemplo02.service;

public class SprintCompletoException extends Exception {
	public SprintCompletoException () {
		super("El sprint ya tiene todas las tareas posibles asignadas");
	}
}
