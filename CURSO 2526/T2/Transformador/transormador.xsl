<?xml version="1.0" encoding="UTF-8" ?>
<stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <!--esto es un comentario-->
    <xsl:template match="/">
        <!--Todo el contenido que voy a transformar-->
        <html lang="en">
            <head>
                <meta charset="utf-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <title>productos</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
                      integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
                      crossorigin="anonymous"/>
                <stylesheet src=""/>
            </head>
            <body>
                <div class="container mt-4">
                    <h1>Listado peliculas</h1>
                    <div class="row">
                        <xsl:for-each select="peliculas/pelicula">
                            <div class="col">
                                <div class="card" style="width: 18rem;">
                                    <div class="card-body">
                                        <xsl:variable name="imagen" select="@poster"/>
                                        <img class="card-img-top" alt="..." src="{$imagen}"/>
                                        <h5 class="card-title">
                                            <xsl:value-of select="@titulo"/>
                                        </h5>
                                        <p class="card-text">
                                            <xsl:value-of select="sinopsis"/>
                                        </p>
                                        <h5>Actores</h5>
                                        <ul class="list-group">
                                            <xsl:for-each select="personajes/personaje">
                                                <li class="list-group-item">
                                                    <xsl:value-of select="@actor"/>
                                                </li>
                                            </xsl:for-each>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </xsl:for-each>
                    </div>

                </div>

            </body>
        </html>
    </xsl:template>
</stylesheet>