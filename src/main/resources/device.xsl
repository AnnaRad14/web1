<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml"
                indent="yes"
                encoding="UTF-8"/>


    <xsl:template match="/Device">

        <Critical>

            <xsl:for-each
                    select="Component[Critical='true']">

                <Component>

                    <Name>
                        <xsl:value-of select="Name"/>
                    </Name>

                    <Origin>
                        <xsl:value-of select="Origin"/>
                    </Origin>

                    <Price>
                        <xsl:value-of select="Price"/>
                    </Price>

                    <Type>

                        <Peripheral>
                            <xsl:value-of
                                    select="Type/Peripheral"/>
                        </Peripheral>

                        <PowerConsumption>
                            <xsl:value-of
                                    select="Type/PowerConsumption"/>
                        </PowerConsumption>

                        <Cooler>
                            <xsl:value-of
                                    select="Type/Cooler"/>
                        </Cooler>

                        <Group>
                            <xsl:value-of
                                    select="Type/Group"/>
                        </Group>

                        <Port>
                            <xsl:value-of
                                    select="Type/Port"/>
                        </Port>

                    </Type>

                    <Critical>
                        <xsl:value-of
                                select="Critical"/>
                    </Critical>

                </Component>

            </xsl:for-each>

        </Critical>

    </xsl:template>

</xsl:stylesheet>