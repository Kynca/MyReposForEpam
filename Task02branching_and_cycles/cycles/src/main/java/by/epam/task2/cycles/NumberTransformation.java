package by.epam.task2.cycles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NumberTransformation {

    static final Logger rootLogger= LogManager.getRootLogger();

    /**
     * This function compare every simbol of char array with rom numbers, than transforms it to int number
     * @param romNumbers array of char simbols which stores roms numbers for transform
     * @return int transformed number
     */
    public int romToArab(char[] romNumbers) {
        if(!orderChecker(romNumbers)){
            throw new IllegalArgumentException("Illegal Order of rom numbers");
        }
        int result = 0;
        for (int i = 0; i < romNumbers.length; i++) {
            switch (romNumbers[i]) {
                case 'M':
                    result += 1000;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'C':
                    if (i + 1 != romNumbers.length && romNumbers[i + 1] == 'D') {
                        result += 400;
                        i++;
                    } else if (i + 1 != romNumbers.length && romNumbers[i + 1] == 'M') {
                        result += 900;
                        i++;
                    } else {
                        result += 100;
                    }
                    break;
                case 'X':
                    if (i + 1 != romNumbers.length && romNumbers[i + 1] == 'L') {
                        result += 40;
                        i++;
                    } else if (i + 1 != romNumbers.length && romNumbers[i + 1] == 'C') {
                        result += 90;
                        i++;
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'I':
                    if (i + 1 != romNumbers.length && romNumbers[i + 1] == 'V') {
                        result += 4;
                        i++;
                    } else if (i + 1 != romNumbers.length && romNumbers[i + 1] == 'X') {
                        result += 9;
                        i++;
                    } else {
                        result++;
                    }
                    break;
                default:
                    rootLogger.error("User enter wrong data");
                    throw new IllegalArgumentException("There is no such rom numbers");
            }
        }
        rootLogger.info("result of transformation is"+result);
        return result;
    }

    /**
     * checks with a sequеnce of rom numerals
     * @param romNumbers array of char simbols which stores roms numbers for transform
     * @return boolean result of checking right sequence
     */
    private boolean orderChecker(char[] romNumbers) {
        var result = true;
        for (int i = 0; i < romNumbers.length; i++) {
            if (i + 1 != romNumbers.length&&result) {
                switch (romNumbers[i]) {
                    case 'M':
                       continue;
                    case 'D':
                        if(romNumbers[i+1]=='M'){
                            result=false;
                        }
                        break;
                    case 'C':
                        if(romNumbers[i+1]=='M'&&i+2!=romNumbers.length&&romNumbers[i+2]=='M'||
                                romNumbers[i+1]=='D'&&i+2!=romNumbers.length&&romNumbers[i+2]=='D'){
                            result=false;
                        }
                        break;
                    case 'X':
                        if(romNumbers[i+1]=='M'||romNumbers[i+1]=='D'){
                            result=false;
                        }
                        if(romNumbers[i+1]=='C'&&i+2!=romNumbers.length&&romNumbers[i+2]=='C'||
                                romNumbers[i+1]=='L'&&i+2!=romNumbers.length&&romNumbers[i+2]=='L'){
                            result=false;
                        }
                        break;
                    case 'L':
                        if(romNumbers[i+1]=='M'||romNumbers[i+1]=='D'||romNumbers[i+1]=='C'){
                            result=false;
                        }
                        break;
                    case 'V':
                        if(romNumbers[i+1]=='M'||romNumbers[i+1]=='D'||romNumbers[i+1]=='C'||romNumbers[i+1]=='X'||
                                romNumbers[i+1]=='L'){
                            result=false;
                        }
                        break;
                    case 'I':
                        if(romNumbers[i+1]=='M'||romNumbers[i+1]=='D'||romNumbers[i+1]=='C'||romNumbers[i+1]=='X'||
                                romNumbers[i+1]=='L'){
                            result=false;
                        }
                        if(romNumbers[i+1]=='X'&&i+2!=romNumbers.length&&romNumbers[i+2]=='X'||
                                romNumbers[i+1]=='V'&&i+2!=romNumbers.length&&romNumbers[i+2]=='V'){
                            result=false;
                        }
                        break;
                    default:
                        rootLogger.error("User enter wrong data");
                        throw new IllegalArgumentException("There is no such rom numbers");
                }
            }
        }
        return result;
    }
}
