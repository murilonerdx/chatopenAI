package com.murilonerdx.basico;

import java.util.Random;
import javax.sound.midi.*;

public class MusicGenerator {
    // Lista de notas musicais
    private static final int[] NOTAS = {60, 62, 64, 65, 67, 69, 71, 72};
    // Lista de durações de nota
    private static final int[] DURACOES = {250, 500, 750, 1000};

    public static void main(String[] args) {
        try {
            // Cria um sequenciador MIDI e um conjunto de pistas
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            // Gera música aleatória
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                // Escolhe uma nota e uma duração aleatórias
                int nota = NOTAS[random.nextInt(NOTAS.length)];
                int duracao = DURACOES[random.nextInt(DURACOES.length)];

                // Adiciona a nota ao track
                track.add(makeEvent(144, 1, nota, 100, i));
                track.add(makeEvent(128, 1, nota, 100, i + duracao));
            }

            // Inicia a reprodução da música
            sequencer.setSequence(sequence);
            sequencer.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Cria um evento MIDI com os parâmetros especificados
    public static MidiEvent makeEvent(int comando, int canal, int nota, int intensidade, int tick) {
        MidiEvent evento = null;
        try {
            ShortMessage mensagem = new ShortMessage();
            mensagem.setMessage(comando, canal, nota, intensidade);
            evento = new MidiEvent(mensagem, tick);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return evento;
    }
}
