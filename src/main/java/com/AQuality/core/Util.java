package com.AQuality.core;

import com.AQuality.commands.Command;
import com.AQuality.commands.CountriesCommand;
import com.AQuality.commands.PollutionWeatherCommand;
import com.AQuality.commands.ReactableCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.*;

public class Util
{
    public static final String PREFIX = "aq!";
    public static final String AIRVISUALAPIKEY;
    public static final String DISCORDBOTTOKEN;
    public static final String GOOGLEMAPSAPIKEY;
    public static final String LEFTARROW = "⬅";
    public static final String RIGHTARROW = "➡";

    public static GatewayDiscordClient gatewayDiscordClient;

    public static Map<String, String> countryCodes = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    public static Map<String, Command> commandToConsumer= new TreeMap<>();

    private static TreeMap<Snowflake, ReactableCommand> reactToConsumer = new TreeMap<>();

    public static void setGatewayDiscordClient(GatewayDiscordClient gatewayDiscordClient) {
        Util.gatewayDiscordClient = gatewayDiscordClient;
    }

    public static void onReact(Snowflake messagesReactedTo, ReactionAddEvent event)
    {
        if (event.getUserId().equals(gatewayDiscordClient.getSelfId()))
        {
            return;
        }
        else if (!reactToConsumer.containsKey(messagesReactedTo))
        {
            return;
        }
        reactToConsumer.get(messagesReactedTo).onReact(event);
    }

    public static void addToReactToConsumer(Snowflake snowflake, ReactableCommand obj)
    {
        reactToConsumer.put(snowflake, obj);
        if (reactToConsumer.size() > 1000) //help prevent a "memory leak" type issue with continually storing messages sent
        {
            reactToConsumer.remove(reactToConsumer.firstKey());
        }
    }

    public static String urlReader(URLConnection connection) throws Exception {
        BufferedReader in;
        if ( ((HttpURLConnection) connection).getResponseCode() >= 400)
        {
            in = new BufferedReader(new InputStreamReader(
                    ((HttpURLConnection) connection).getErrorStream()));
        }
        else
        {
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
        }

        String inputLine;
        StringBuilder allTokens = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
        {
            allTokens.append(inputLine);
        }
        in.close();
        return allTokens.toString();
    }

    static
    {
        commandToConsumerFactory();
        countryToCountryCodeFactory();

        ObjectMapper objectMapper = new ObjectMapper();
        Credentials creds = null;
        try {
            creds = objectMapper.readValue(new File("src/main/resources/config.json"), Credentials.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
        AIRVISUALAPIKEY = creds.getAirVisualApiKey();
        DISCORDBOTTOKEN = creds.getDiscordBotToken();
        GOOGLEMAPSAPIKEY = creds.getGoogleMapsApiKey();
    }

    public static void commandToConsumerFactory()
    {
        commandToConsumer.put("countries", new CountriesCommand());
        commandToConsumer.put("pollution", new PollutionWeatherCommand());
        commandToConsumer.put("weather", new PollutionWeatherCommand());
    }

    /**
     * code curtsy of https://stackoverflow.com/questions/14155049/iso2-country-code-from-country-name
     */
    public static void countryToCountryCodeFactory()
    {
        countryCodes.put("Andorra", "AD");
        countryCodes.put("United Arab Emirates", "AE");
        countryCodes.put("Afghanistan", "AF");
        countryCodes.put("Antigua And Barbuda", "AG");
        countryCodes.put("Anguilla", "AI");
        countryCodes.put("Albania", "AL");
        countryCodes.put("Armenia", "AM");
        countryCodes.put("Netherlands Antilles", "AN");
        countryCodes.put("Angola", "AO");
        countryCodes.put("Antarctica", "AQ");
        countryCodes.put("Argentina", "AR");
        countryCodes.put("American Samoa", "AS");
        countryCodes.put("Austria", "AT");
        countryCodes.put("Australia", "AU");
        countryCodes.put("Aruba", "AW");
        countryCodes.put("Azerbaidjan", "AZ");
        countryCodes.put("Bosnia Herzegovina", "BA");
        countryCodes.put("Barbados", "BB");
        countryCodes.put("Bangladesh", "BD");
        countryCodes.put("Belgium", "BE");
        countryCodes.put("Burkina Faso", "BF");
        countryCodes.put("Bulgaria", "BG");
        countryCodes.put("Bahrain", "BH");
        countryCodes.put("Burundi", "BI");
        countryCodes.put("Benin", "BJ");
        countryCodes.put("Bermuda", "BM");
        countryCodes.put("Brunei Darussalam", "BN");
        countryCodes.put("Bolivia", "BO");
        countryCodes.put("Brazil", "BR");
        countryCodes.put("Bahamas", "BS");
        countryCodes.put("Bhutan", "BT");
        countryCodes.put("Bouvet Island", "BV");
        countryCodes.put("Botswana", "BW");
        countryCodes.put("Belarus", "BY");
        countryCodes.put("Belize", "BZ");
        countryCodes.put("Canada", "CA");
        countryCodes.put("Cocos (Keeling) Islands", "CC");
        countryCodes.put("Central African Republic", "CF");
        countryCodes.put("Congo, The Democratic Republic Of The", "CD");
        countryCodes.put("Congo", "CG");
        countryCodes.put("Switzerland", "CH");
        countryCodes.put("Ivory Coast", "CI");
        countryCodes.put("Cook Islands", "CK");
        countryCodes.put("Chile", "CL");
        countryCodes.put("Cameroon", "CM");
        countryCodes.put("China", "CN");
        countryCodes.put("Colombia", "CO");
        countryCodes.put("Costa Rica", "CR");
        countryCodes.put("Former Czechoslovakia", "CS");
        countryCodes.put("Cuba", "CU");
        countryCodes.put("Cape Verde", "CV");
        countryCodes.put("Christmas Island", "CX");
        countryCodes.put("Cyprus", "CY");
        countryCodes.put("Czech Republic", "CZ");
        countryCodes.put("Germany", "DE");
        countryCodes.put("Djibouti", "DJ");
        countryCodes.put("Denmark", "DK");
        countryCodes.put("Dominica", "DM");
        countryCodes.put("Dominican Republic", "DO");
        countryCodes.put("Algeria", "DZ");
        countryCodes.put("Ecuador", "EC");
        countryCodes.put("Estonia", "EE");
        countryCodes.put("Egypt", "EG");
        countryCodes.put("Western Sahara", "EH");
        countryCodes.put("Eritrea", "ER");
        countryCodes.put("Spain", "ES");
        countryCodes.put("Ethiopia", "ET");
        countryCodes.put("Finland", "FI");
        countryCodes.put("Fiji", "FJ");
        countryCodes.put("Falkland Islands", "FK");
        countryCodes.put("Micronesia", "FM");
        countryCodes.put("Faroe Islands", "FO");
        countryCodes.put("France", "FR");
        countryCodes.put("France (European Territory)", "FX");
        countryCodes.put("Gabon", "GA");
        countryCodes.put("Great Britain", "UK");
        countryCodes.put("Grenada", "GD");
        countryCodes.put("Georgia", "GE");
        countryCodes.put("French Guyana", "GF");
        countryCodes.put("Ghana", "GH");
        countryCodes.put("Gibraltar", "GI");
        countryCodes.put("Greenland", "GL");
        countryCodes.put("Gambia", "GM");
        countryCodes.put("Guinea", "GN");
        countryCodes.put("Guadeloupe (French)", "GP");
        countryCodes.put("Equatorial Guinea", "GQ");
        countryCodes.put("Greece", "GR");
        countryCodes.put("S. Georgia & S. Sandwich Isls.", "GS");
        countryCodes.put("Guatemala", "GT");
        countryCodes.put("Guam (USA)", "GU");
        countryCodes.put("Guinea Bissau", "GW");
        countryCodes.put("Guyana", "GY");
        countryCodes.put("Hong Kong SAR", "HK");
        countryCodes.put("Heard And McDonald Islands", "HM");
        countryCodes.put("Honduras", "HN");
        countryCodes.put("Croatia", "HR");
        countryCodes.put("Haiti", "HT");
        countryCodes.put("Hungary", "HU");
        countryCodes.put("Indonesia", "ID");
        countryCodes.put("Ireland", "IE");
        countryCodes.put("Israel", "IL");
        countryCodes.put("India", "IN");
        countryCodes.put("British Indian Ocean Territory", "IO");
        countryCodes.put("Iraq", "IQ");
        countryCodes.put("Iran", "IR");
        countryCodes.put("Iceland", "IS");
        countryCodes.put("Italy", "IT");
        countryCodes.put("Jamaica", "JM");
        countryCodes.put("Jordan", "JO");
        countryCodes.put("Japan", "JP");
        countryCodes.put("Kenya", "KE");
        countryCodes.put("Kyrgyzstan", "KG");
        countryCodes.put("Cambodia, Kingdom Of", "KH");
        countryCodes.put("Kiribati", "KI");
        countryCodes.put("Comoros", "KM");
        countryCodes.put("Saint Kitts & Nevis Anguilla", "KN");
        countryCodes.put("North Korea", "KP");
        countryCodes.put("South Korea", "KR");
        countryCodes.put("Kuwait", "KW");
        countryCodes.put("Cayman Islands", "KY");
        countryCodes.put("Kazakhstan", "KZ");
        countryCodes.put("Laos", "LA");
        countryCodes.put("Lebanon", "LB");
        countryCodes.put("Saint Lucia", "LC");
        countryCodes.put("Liechtenstein", "LI");
        countryCodes.put("Sri Lanka", "LK");
        countryCodes.put("Liberia", "LR");
        countryCodes.put("Lesotho", "LS");
        countryCodes.put("Lithuania", "LT");
        countryCodes.put("Luxembourg", "LU");
        countryCodes.put("Latvia", "LV");
        countryCodes.put("Libya", "LY");
        countryCodes.put("Morocco", "MA");
        countryCodes.put("Monaco", "MC");
        countryCodes.put("Moldavia", "MD");
        countryCodes.put("Madagascar", "MG");
        countryCodes.put("Marshall Islands", "MH");
        countryCodes.put("North Macedonia", "MK");
        countryCodes.put("Mali", "ML");
        countryCodes.put("Myanmar", "MM");
        countryCodes.put("Mongolia", "MN");
        countryCodes.put("Macao SAR", "MO");
        countryCodes.put("Northern Mariana Islands", "MP");
        countryCodes.put("Martinique (French)", "MQ");
        countryCodes.put("Mauritania", "MR");
        countryCodes.put("Montserrat", "MS");
        countryCodes.put("Malta", "MT");
        countryCodes.put("Mauritius", "MU");
        countryCodes.put("Maldives", "MV");
        countryCodes.put("Malawi", "MW");
        countryCodes.put("Mexico", "MX");
        countryCodes.put("Malaysia", "MY");
        countryCodes.put("Mozambique", "MZ");
        countryCodes.put("Namibia", "NA");
        countryCodes.put("New Caledonia", "NC");
        countryCodes.put("Niger", "NE");
        countryCodes.put("Norfolk Island", "NF");
        countryCodes.put("Nigeria", "NG");
        countryCodes.put("Nicaragua", "NI");
        countryCodes.put("Netherlands", "NL");
        countryCodes.put("Norway", "NO");
        countryCodes.put("Nepal", "NP");
        countryCodes.put("Nauru", "NR");
        countryCodes.put("Neutral Zone", "NT");
        countryCodes.put("Niue", "NU");
        countryCodes.put("New Zealand", "NZ");
        countryCodes.put("Oman", "OM");
        countryCodes.put("Panama", "PA");
        countryCodes.put("Peru", "PE");
        countryCodes.put("Polynesia (French)", "PF");
        countryCodes.put("Papua New Guinea", "PG");
        countryCodes.put("Philippines", "PH");
        countryCodes.put("Pakistan", "PK");
        countryCodes.put("Poland", "PL");
        countryCodes.put("Saint Pierre And Miquelon", "PM");
        countryCodes.put("Pitcairn Island", "PN");
        countryCodes.put("Puerto Rico", "PR");
        countryCodes.put("Portugal", "PT");
        countryCodes.put("Palau", "PW");
        countryCodes.put("Paraguay", "PY");
        countryCodes.put("Qatar", "QA");
        countryCodes.put("Reunion (French)", "RE");
        countryCodes.put("Romania", "RO");
        countryCodes.put("Russia", "RU");
        countryCodes.put("Rwanda", "RW");
        countryCodes.put("Saudi Arabia", "SA");
        countryCodes.put("Solomon Islands", "SB");
        countryCodes.put("Seychelles", "SC");
        countryCodes.put("Sudan", "SD");
        countryCodes.put("Sweden", "SE");
        countryCodes.put("Singapore", "SG");
        countryCodes.put("Saint Helena", "SH");
        countryCodes.put("Slovenia", "SI");
        countryCodes.put("Svalbard And Jan Mayen Islands", "SJ");
        countryCodes.put("Slovak Republic", "SK");
        countryCodes.put("Sierra Leone", "SL");
        countryCodes.put("San Marino", "SM");
        countryCodes.put("Senegal", "SN");
        countryCodes.put("Somalia", "SO");
        countryCodes.put("Suriname", "SR");
        countryCodes.put("Saint Tome (Sao Tome) And Principe", "ST");
        countryCodes.put("Former USSR", "SU");
        countryCodes.put("El Salvador", "SV");
        countryCodes.put("Syria", "SY");
        countryCodes.put("Swaziland", "SZ");
        countryCodes.put("Turks And Caicos Islands", "TC");
        countryCodes.put("Chad", "TD");
        countryCodes.put("French Southern Territories", "TF");
        countryCodes.put("Togo", "TG");
        countryCodes.put("Thailand", "TH");
        countryCodes.put("Tadjikistan", "TJ");
        countryCodes.put("Tokelau", "TK");
        countryCodes.put("Turkmenistan", "TM");
        countryCodes.put("Tunisia", "TN");
        countryCodes.put("Tonga", "TO");
        countryCodes.put("East Timor", "TP");
        countryCodes.put("Turkey", "TR");
        countryCodes.put("Trinidad And Tobago", "TT");
        countryCodes.put("Tuvalu", "TV");
        countryCodes.put("Taiwan", "TW");
        countryCodes.put("Tanzania", "TZ");
        countryCodes.put("Ukraine", "UA");
        countryCodes.put("Uganda", "UG");
        countryCodes.put("United Kingdom", "UK");
        countryCodes.put("USA Minor Outlying Islands", "UM");
        countryCodes.put("USA", "US");
        countryCodes.put("Uruguay", "UY");
        countryCodes.put("Uzbekistan", "UZ");
        countryCodes.put("Holy See (Vatican City State)", "VA");
        countryCodes.put("Saint Vincent & Grenadines", "VC");
        countryCodes.put("Venezuela", "VE");
        countryCodes.put("Virgin Islands (British)", "VG");
        countryCodes.put("U.S. Virgin Islands", "VI");
        countryCodes.put("Vietnam", "VN");
        countryCodes.put("Vanuatu", "VU");
        countryCodes.put("Wallis And Futuna Islands", "WF");
        countryCodes.put("Samoa", "WS");
        countryCodes.put("Yemen", "YE");
        countryCodes.put("Mayotte", "YT");
        countryCodes.put("Yugoslavia", "YU");
        countryCodes.put("South Africa", "ZA");
        countryCodes.put("Zambia", "ZM");
        countryCodes.put("Zaire", "ZR");
        countryCodes.put("Zimbabwe", "ZW");

        countryCodes.put("Serbia", "RS");
        countryCodes.put("Slovakia", "SK");
    }


    /**
     * Code curtsy of https://attacomsian.com/blog/how-to-convert-country-code-to-emoji-in-java
     */
    public static String countryNameToEmoji(String country)
    {

        if (country.equalsIgnoreCase("Kosovo"))
        {
            return "\uD83C\uDDFD\uD83C\uDDF0";
        }

        String code = countryCodes.get(country);
        // offset between uppercase ascii and regional indicator symbols
        int OFFSET = 127397;

        // validate code
        if(code == null || code.length() != 2) {
            return "";
        }

        //fix for uk -> gb
        if (code.equalsIgnoreCase("uk")) {
            code = "gb";
        }

        // convert code to uppercase
        code = code.toUpperCase();

        StringBuilder emojiStr = new StringBuilder();

        //loop all characters
        for (int i = 0; i < code.length(); i++) {
            emojiStr.appendCodePoint(code.charAt(i) + OFFSET);
        }

        // return emoji
        return emojiStr.toString();

    }

    /**
     * gets the command from a message string,
     * @param str string starts with prefix
     * @return returns command
     */
    public static String getCommand(String str)
    {
        String ans = "";
        str = str.toLowerCase();

        if (!isValidString(str))
        {
            return ans;
        }
        for (int i = PREFIX.length(); i < str.length(); i++)
        {
            char currChar = str.charAt(i);
            if (Character.isWhitespace(currChar))
            {
                break;
            }
            ans += currChar;
        }
        return ans.toLowerCase();
    }

    /**
     * checks if message is a message for the bot
     * @param str whole message
     * @return true or false based on if the message is directed towards the bot or not
     */
    public static boolean isValidString(String str)
    {
        if (str.length() <= PREFIX.length()
                || !str.substring(0, PREFIX.length()).equalsIgnoreCase(PREFIX))
        {
            return false;
        }
        return true;
    }

    public static List<String> getInputParams(String message)
    {
        ArrayList<String> str = new ArrayList<>();
        if (!isValidString(message))
        {
            return str;
        }
        int startIndex = PREFIX.length() + (getCommand(message).length()) +1;
        //aq!coun USA
        //01234456789
        //3+4+1 = 7
        String currStr = "";
        for (int i = startIndex; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (Character.isWhitespace(c))
            {
                str.add(currStr);
                currStr = "";
            }
            else
            {
                currStr += c;
            }
        }
        if (currStr.length() > 0)
        {
            str.add(currStr);
        }

        return str;
    }

    /**
     *
     * @param command prerequisite: string has already been passed through the getCommand() method
     * @param event event where the command was ran
     */
    public static void runCommand(String command, MessageCreateEvent event )
    {
        if (commandToConsumer.containsKey(command))
        {
            commandToConsumer.get(command).createNew().accept(event);
        }
    }

    public static boolean isInputType(List<String> inputs, Class... clazz)
    {
        if (inputs.size() != clazz.length)
        {
            return false;
        }
        for (int i = 0; i < inputs.size(); i++)
        {
            if (clazz[i] == Integer.class)
            {
                try {
                    Integer.parseInt(inputs.get(i));
                }
                catch (NumberFormatException e)
                {
                    return false;
                }
            }
            else if (clazz[i] == Double.class)
            {
                try {
                    Double.parseDouble(inputs.get(i));
                }
                catch (NumberFormatException e)
                {
                    return false;
                }
            }
        }
        return true;
    }



}
