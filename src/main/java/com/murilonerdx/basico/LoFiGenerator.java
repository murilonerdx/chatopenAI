package com.murilonerdx.basico;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class LoFiGenerator {
    public static void main(String[] args) {
        try {
            // Cria um novo arquivo MIDI
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();
            // Define os parâmetros do arquivo MIDI
            MetaMessage message = new MetaMessage();
            message.setMessage(0x03, "Lo-Fi".getBytes(), "Lo-Fi".length());
            MidiEvent event = new MidiEvent(message, 0);
            track.add(event);

            ShortMessage bpm = new ShortMessage();
            bpm.setMessage(144,9,56,100);
            MidiEvent bpmEvent = new MidiEvent(bpm, 0);
            track.add(bpmEvent);

            // Adiciona os acordes da música
            String[] chords = {"Am", "C", "G", "D", "Am", "C", "G", "D", "Am", "C", "G", "D", "Am", "C", "Em"};


            // Cria a estrutura de repetição da música
            int time = 4;
            for (int i = 0; i < 4; i++) {
                for (String chord : chords) {
                    // Adiciona o acorde à faixa
                    ShortMessage note = new ShortMessage();
                    note.setMessage(0x90, getMidiPitch(chord), 0x60);
                    MidiEvent noteEvent = new MidiEvent(note, time);
                    track.add(noteEvent);
                    time += 4;
                }
            }

            // Escreve o arquivo MIDI em disco
            File file = new File("lo-fi.mid");
            MidiSystem.write(sequence, 1, file);
        } catch (InvalidMidiDataException | IOException | MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Retorna a pitch MIDI correspondente a um acorde dado
    private static int getMidiPitch(String chord) {
        switch (chord) {
            case "C":
                return 0x3C;
            case "G":
                return 0x47;
            case "Am":
                return 0x45;
            case "D":
                return 0x44;
            case "F":
                return 0x46;
            default:
                return 0;
        }
    }
}
