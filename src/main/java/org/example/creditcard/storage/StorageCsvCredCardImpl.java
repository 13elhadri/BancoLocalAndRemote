package org.example.creditcard.storage;

import org.example.creditcard.repositories.CreditCardRepository;
import org.example.models.TarjetaCredito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Implementaci�n del almacenamiento de tarjetas de cr�dito en formato CSV.
 * Esta clase proporciona m�todos para importar y exportar una lista de tarjetas de cr�dito
 * en formato CSV.
 * @author Ra�l Fern�ndez, Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Samuel Cort�s, Yahya El Hadri.
 * @since 1.0
 */

public class StorageCsvCredCardImpl implements StorageCsvCredCard {
    private final Logger logger = LoggerFactory.getLogger(CreditCardRepository.class);

    /**
     * Importa una lista de tarjetas de cr�dito desde un archivo CSV.
     * @author Ra�l Fern�ndez, Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Samuel Cort�s, Yahya El Hadri.
     * @since 1.0
     * @param file archivo CSV con la lista de tarjetas de cr�dito
     * @return flujo de tarjetas de cr�dito importadas
     */

    @Override
    public Flux<TarjetaCredito> importList(File file) {
        logger.debug("Import tarjetas de Credito from file: {}", file.getAbsolutePath());
        return Flux.<TarjetaCredito>create(emitter -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                reader.lines()
                        .skip(1)
                        .forEach(line -> {
                            TarjetaCredito tarjetaCredito = parseLine(List.of(line.split(",")));
                            emitter.next(tarjetaCredito);
                        });
                emitter.complete();
            } catch (Exception e) {
                emitter.error(e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
    /**
     * Convierte una l�nea del archivo CSV en una tarjeta de cr�dito.
     * @author Ra�l Fern�ndez, Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Samuel Cort�s, Yahya El Hadri.
     * @since 1.0
     * @param linea l�nea del archivo CSV
     * @return tarjeta de cr�dito
     */

    private TarjetaCredito parseLine(List<String> linea) {
        return TarjetaCredito.builder()
                .id(UUID.fromString(linea.get(0)))
                .numero( linea.get(1))
                .nombreTitular(linea.get(2))
                .clientID(Long.parseLong(linea.get(3)))
                .fechaCaducidad(linea.get(4))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isDeleted(false)
                .build();
    }

    /**
     * Exporta una lista de tarjetas de cr�dito a un archivo CSV.
     * @author Ra�l Fern�ndez, Alvaro Herrero, Javier Ru�z, Javier Hern�ndez, Samuel Cort�s, Yahya El Hadri.
     * @since 1.0
     * @param lista lista de tarjetas de cr�dito
     * @param file archivo CSV con la lista de tarjetas de cr�dito
     */

    @Override
    public void exportList(List<TarjetaCredito> lista, File file) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("id,numero,nombreTitular,clientID,fechaCaducidad");
            writer.newLine();

            for (TarjetaCredito tarjeta : lista) {
                String line = tarjeta.getId() + "," +
                        tarjeta.getNumero() + "," +
                        tarjeta.getNombreTitular() + "," +
                        tarjeta.getClientID() + "," +
                        tarjeta.getFechaCaducidad();

                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

