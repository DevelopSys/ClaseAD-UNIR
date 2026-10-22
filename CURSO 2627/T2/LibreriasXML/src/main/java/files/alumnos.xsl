<?xml version="1.0" encoding="UTF-8"?>
<stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <template match="/">

        <html lang="en">
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Document</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@6.0.0-alpha.1/dist/css/bootstrap.min.css"
                      rel="stylesheet"
                      integrity="sha384-B/GM4XqrwHnWXNOWMbloTmrYXZg10cakYGmpfsR/bbzQ6JAJI4ihuyADKLnBgrCe"
                      crossorigin="anonymous"/>

            </head>
            <body>
                <h1>Resultados</h1>
                <p>Resultados del examen de AD de los alumnos. A continuacion mostraremos una tabla con todos los
                    datos
                </p>
                <p>El total de alumno del fichero es <xsl:value-of select="count(/agenda/data)"/> </p>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellido</th>
                            <th scope="col">Dni</th>
                            <th scope="col">Nota</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="/agenda/data">
                            <tr>
                                <td>
                                    <!--/agenda/data/nombre-->
                                    <xsl:value-of select="nombre"/>
                                </td>
                                <td>
                                    <!--/agenda/data/apellido-->
                                    <xsl:value-of select="apellido"/>
                                </td>
                                <td>
                                    <xsl:value-of select="@dni"/>
                                </td>
                                <td>
                                    <!--/agenda/data/nota-->
                                    <xsl:value-of select="nota"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </template>
</stylesheet>
