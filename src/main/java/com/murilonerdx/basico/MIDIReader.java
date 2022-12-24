package com.murilonerdx.basico;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class MIDIReader {
    public static void main(String[] args) {
        try {
            // Lê o arquivo MIDI
            File file = new File("music.mid");
            Sequence sequence = MidiSystem.getSequence(file);

            // Obtém as faixas do arquivo MIDI
            Track[] tracks = sequence.getTracks();
            for (Track track : tracks) {
                // Obtém os eventos da faixa
                for (int i = 0; i < track.size(); i++) {
                    MidiEvent event = track.get(i);
                    MidiMessage message = event.getMessage();
                    long tick = event.getTick();

                    // Verifica o tipo de mensagem MIDI e imprime a informação correspondente
                    if (message instanceof ShortMessage) {
                        ShortMessage sm = (ShortMessage) message;
                        System.out.println("Tick: " + tick + " Command: " + sm.getCommand() + " Data1: " + sm.getData1() + " Data2: " + sm.getData2());
                    } else if (message instanceof MetaMessage) {
                        MetaMessage mm = (MetaMessage) message;
                        if (mm.getType() == 0x03) {
                            System.out.println("Tick: " + tick + " Track name: " + new String(mm.getData()));
                        } else if (mm.getType() == 0x51) {
                            int bpm = mm.getData()[2] & 0xff | (mm.getData()[1] & 0xff) << 8 | (mm.getData()[0] & 0xff) << 16;
                            System.out.println("Tick: " + tick + " BPM: " + bpm);
                        }
                    }
                }
            }
        } catch (InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
    }
}
