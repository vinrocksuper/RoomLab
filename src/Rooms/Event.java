package Rooms;

import Entities.Person;
import Game.Runner;

import java.util.Scanner;

import static Game.Runner.gameOff;

public class Event extends Room
{
    private int randomEvent = (int)(Math.random()*6);
    private boolean[] eventClear = {false,false,false,false,false};


    public Event(int x, int y)
    {
        super(x, y);
        this.type = "event";
    }

    /**
     * Calls the function to generate an event and sets the player into the location.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {

        if(x.poisoned)
        {
            System.out.println("The poison eats away at you. You take 5 damage.");
            x.hp -= 5;
            System.out.println("You now have " + x.hp + " health");
            if(x.hp<=0)
            {
                gameOff();
            }
        }
        getEvent(x);
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);

    }

    /**
     * takes the person out of the room
     * @param x the player
     */
    public void leaveRoom(Person x)
    {
        occupant = null;
    }

    /**
     * Generates an event for a room.
     * @param x Player will be effected by the event
     */
    private void getEvent(Person x)
    {
        String[] events = {"You found a map!","You accidentally stub your toe.","You find a treasure chest!","There's a potion on the ground.","You hit your head and now you have amnesia.", "You find a small pouch of gold on the ground"};
        String str = events[randomEvent];
        System.out.println(str);
        if(randomEvent == 4)
        {
            if(!eventClear[4]) {
                x.amnesia = true;
                if (x.pill) {
                    System.out.println("How peculiar, even though your head hurts, you can recall everything just fine.");
                    x.amnesia = false;
                }

                eventClear[4] = true;
            }
        }
        if(randomEvent == 0)
        {
            x.map = true;
            eventClear[0]=true;
        }
        if(randomEvent == 1)
        {
            System.out.println("You take 10 damage.");
            x.hp -= 10;
            System.out.println("You now have " + x.hp + " health");
            eventClear[1]=true;
            if(x.hp<=0)
            {
                Runner.gameOff();
                System.out.println("Wow that's embarrassing.    \"                                                                         `                                \n" +
                        "                                                                      ``        `      `                        \n" +
                        "                                                                        ....,,. ```                             \n" +
                        "                                                                      ``,::,,,.``..``.`                         \n" +
                        "                                                                     `.,,::,,,,..,...,,`                        \n" +
                        "                                                                     `.:..,,,,::,.......`                       \n" +
                        "                                                                    ``..```.,,,::,,,.``.```                     \n" +
                        "                                                                   `.,.`,..,,.,::;;:,..`...``                   \n" +
                        "                                                   `             `.....,,.,,,,,::,:;..,,,`..``                  \n" +
                        "                                                 `   ``         ``..``.....,,,,,,,;;,.,,,.,..`                  \n" +
                        "                                                   ``        `.:;;iiiiii*i;:,,,,,.,,,,,.,:,,:.`                 \n" +
                        "                                                          `,*znnxMMMMWWMMMMnz+;,..,::,.`,:,,;:.                 \n" +
                        "                                                   `    `*xWWWWWWWWWWWWWMWWWWWMn*;,,,,,,,..,::,                 \n" +
                        "                                                 .  `  #WWWMMWWW@@@@@@@@WWMMMWWMMn*:,,,,,..,,,,`                \n" +
                        "                                                     iWWMMWWWWW@@@@@@@@@WWMWWMMMMMMn*:.,,.,,,,,,`               \n" +
                        "                                                 ` `xWWM@WWWWWW@@@@@@@@WWWMWWMMMMMxMMzi:,,:,,..:``              \n" +
                        "                                             `    iMWMWMWWWWWW@@@@@@@WWWWWWWMMMMMMMxxxnz:.,:,.,,,  `            \n" +
                        "                                            `   `+W@WMMMWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMxnzi,,,:,.,..,`           \n" +
                        "                                         `    .`zWWMWWMWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMxxxn*,.,,..,::.`          \n" +
                        "                                        `    ` zMWMMWMxWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMxxnMx+:..,.,,,,,..`       \n" +
                        "                                            ` nMxMWWWWMMWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMxxxxxz;..,:,,:;:,.`      \n" +
                        "                                           ` nWxWMMMMWWMWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMMMxxxxzi..,,,,,,,,.`     \n" +
                        "                                            nMxMMMWWWMMMWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMxMMxxMzi,..,....,,.`    \n" +
                        "                                           *MWxMMMMMWWWMWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMxMMxxxz;..:,.,,,,,`    \n" +
                        "                                     `    :MMMWMMWWMWWWWWWWWWWWWWWWWWWWWWWWWWWWMWMMMMMMMMMMxxxxn#:.,,:;,,,,``   \n" +
                        "                                    ` `  `WMWMMMMWWWWWMMWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMxxxxxxx+:.,:::::,.`   \n" +
                        "                                    `   `MWWMWWMMWWWWWMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMxxxxxxxni,,:,,::,,    \n" +
                        "                                       .nx##+zxxMWWMWMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMxxxxxxnn#;::,.`,:,`   \n" +
                        "                                      `izii,**iznxMMWMMWMMWWWWWWWWWWWWWWWWWWWWWW@WWWWMMMMMxxxxxxxnz+::;:`.:,.   \n" +
                        "                                 `    :i,::i,:i:*#nMMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMxMMMxxnn#;:;::,,..`` \n" +
                        "                                 `  `.i****i;.`,:i+zxMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMxxxnnzi:::;:,.``  \n" +
                        "                                    `*++#nn##+;,`,:*#nxMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMxxxz#*:,;::,.    \n" +
                        "                                ``  +##zznnnnxn+,.,:i#xxMMWWWMWWWWWWWWWWWWWWWWWWWWWWWWMWMMMxMMMxxnz#*;,;:;:``   \n" +
                        "                        `         `izzzz#znnnxxn+,.:;+nMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMxnn#*;:::;:`    \n" +
                        "                            `      x##xnnnxxnnnnn+;,,+nxMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMxxMMxnnz*i:,,::.    \n" +
                        "                                  #xz+**+++znxMxzz#;:+nnMMMMMMWWWWWWWWWWWWWWWWW@WWWWWWWMMMMMxxMMMnxz*;:,,:,,    \n" +
                        "                                 ,#i*;::.:*++#nxzzz+*#zxMMMMMWWMWMMWWWWWWWWWWWWWWWWWWWWMMMMMxxxMMnxn*;::,,,;.   \n" +
                        "                                `MMMzi*i:..:i#z#nz####zxMMMMMMMMMMMMMWWWWWWWWWWWWWWWWMMMMMMMxxxxxnxz*;::,,,i,   \n" +
                        "                               ,MWWxMx#i++*;,:i+###++znxMMMMMMxnz##zzxW@WWWWWWWWWWWMMMMMMMMMMMxxxxx#i;::,::;;   \n" +
                        "                             `*WWMMWMMn#i*#z#+i:;*+++znMMMMMMxzzii;::i+#MMWWWWWWWWWMMMMMMMMMMMxxxMx+i;:,,::;;   \n" +
                        "                            `zMMMWWWWWMxz+i*#z##+;;i#xMMMMMMnnz+;,,,:,:;*zMWWWWWWWWMMMWMMMMMMMxxMxn+;;:::.,::`  \n" +
                        "                        `   zxMMMWWWWWWMMn#+***+#*+#nMMWMWWxnnz*;;:,,,;i;*#nxWW@WWWWMMMMMMMMMMxxxnn+;;::;.,::`  \n" +
                        "                           iMMMMMWWWWWWWWMxnz####+#zxWWWWMMxn#++##+*ii;:;;i+zxMWWWWMMMMMMMMMMMxxxxn+i::;;,,::.  \n" +
                        "                         ``MxxMMWWWWWWWWMMMMMMMxzznMWWWWWMxxn##nznnnnn#*i;;i+zxWWWMMMMMMMMxxMMxxxxn+i;::i:,::.  \n" +
                        "                          ixMxMMMWWWWWWMMMMMMMMMxxMWWWWMMMMnznnnzznxMMMMz#*ii*zxWWMMMMMMMxxxxxxxxxz*;;:,;:,::.  \n" +
                        "                        ` xnxxMMMMWWWWWWMMMMxxMMMMMWWMMxMxnznzz#z#znMMMMWMn#*i*+MWMxMMxMMMxxxxxxnx#*;;;,::,::.  \n" +
                        "                         :MxzxMxMMWWWWWMMMxxxxMMWWWWWMMMMnznx#i***+zxMMMMMMxz+**zMMxxMxMMMxxxxxxnn#*i;;:,:,,:,  \n" +
                        "                         MznMxxxMMWWWWWMMn##nxMMWWWWMxMxxxxxx+;;i*++#nMMMMxnz#+*+xMxxMxMMMxxxxxxnz++i:::.:.,:,  \n" +
                        "                     `  :zxznxxMMMMWMMxMnznxMMMW@WWMMMxxxxxMxz##;;i#xxzzxxxxz#***nxxxxxMMMMMxxxnz+++i,,;:,.,:.  \n" +
                        "                   `  ` #znnxnnMxMMxxMxzxnMWMMWWWMMMMMMxMxxMxn#+ni,:izMnzxxxMn+iizxxMxxxMxMMxxxnz#**;::;:,.,:.  \n" +
                        "                   `  `:xzznxnxMMxxxxnzzznnnMMMWMMMMMMMMMMMMMxz++zz+:.;#+nMMxxz+izxxxxxxMxxxxxnnz#*i;::,:,.,:.  \n" +
                        "                     ` nzzznnnxxxxnxnzzn#++zMMMWWMxxMMMMMMMMMMx#+##zn*,,*i#MMMMz+zxxxxxxMxxxnnnz#+*i;;,.,,.,:.  \n" +
                        "                    ` .x##nnznxnxxnz#znz+,innxWMMMMMMMMxxMMMMWMxz####z+;.:+nxnnz#znxxxxxMxxxnzz#+*ii;:,,,,,,:.  \n" +
                        "                      +zz#znznxnnz##znnz#,,+zxMxxxMMMMxxxxMMMWWMMxnz###++i:*xn+z+#zxxxMxxxxxnz##+i;;:,,,.,,::`  \n" +
                        "                     `n#z##znnnnz##nnzzz#+;;#nznznxMMMMxxxxMMMMMMMnnMn#z#x#:inxnz#zxxMMxxxxxz##+*i:,,,,,.,,,,`  \n" +
                        "                     i###zzzzzzz+#nnz#+**##ii++*+#znxMMMxnnMWWMMMMMMxMWMMxMniinMMxxMxxMxxxxxz#+*i;,.,,,,.,,,,`  \n" +
                        "                    `n#####zz##+#z#+*;;;i*#+;;i,.,i+zxxxnnnxWMMMMMMMMMMMMMMMMxz#xxxMMxxxMxxxz++*i,,.,:,,:,.,,`  \n" +
                        "                  ` iz##+###z#+##*;i;::;;;**;;:.````:+zz#znxMMMMMMWMMMMMMMMMMMxxxxxxxxxxxxxn#**ii:,.,:,,,..,.`  \n" +
                        "                  ``z####zz#z+*#i;;i;:::::*#+i;::,``.;+#+znnxMMMMWWWMMMMMMMMMMMMxxxxMMxxxxnn#**i;:,,.,.,...,.`  \n" +
                        "                   ;nz###znz#+**:;iii**i:.:*++***i;,..;*#nnnxMMMMWWWWWWWWWMMMMMMMMxxxxxnnnnz+***;:,........,.`  \n" +
                        "                  `#zz##zzxz++*;;;i*+##z*;:i+##+****ii*zxxnzxMMWWWWWWWWWWWWMMMMxMMMxxxxnnnz#+**i:,,..`..`...`   \n" +
                        "                  :nz#z#nnx#*+i,:*++++#zzznz+iiii;i#nnxMMxzzxMWW@@@@@@@WMMMMMMMMxxxxxxxxnn#+***i,,...`..`..`    \n" +
                        "                  +nzzz#nxx+**:,;*i:;i*#zxMM#i::;::i#xMMWMnznMMW@@@@@@@WMMMMMMMxxxxxxxxnnz+*iii;::,,,...````    \n" +
                        "                 `#z#z##nxx***,;i:`.ii;*#znxxz*;;iii+zMMWMnznxMWWWWWWWWWWMMMMMMxxxnnnnnznz*i;ii:;;::,..````     \n" +
                        "                 `zzzzz#nxn#+*;;;`.+x#*#++#nnxn#+#*i+znMWMnznxMMWWWWWWWMMMMMxxxxnnnnnnnzzzi;i;:;ii;:... ``      \n" +
                        "                 ,zzzz#znnn#+**+i,*MMnM@x+i+#nxxxz**#znxMxzznxMMWWWWWWMMMMMMxxxxnnnnnnnzz#i,::;*#;:,.``.`       \n" +
                        "                 izznz#znzn#*+#z#*nWxW@@@xxn+*+nnxxz#*+zxnzznxMMMMMMMMMMMMMxxxxxnzznnnnzz*:,,;*+i;;.`.``        \n" +
                        "                `+#zzz#zznn++zzz+;#nn@@WWW@@n**i#nxz+i*znnz#zxMMMMMMMMMMMMxxxxxnnzznzzzz#;,:i++*i,.`.:.`        \n" +
                        "                .##z#zz#znz+#n#+*:..,*n@@@WWWMni;+znn#+zzzz#znMMMMMMMMMxxxxxxxnnzzznzzzz+:,i***;;,,````         \n" +
                        "                ;##z#zz#zz##nz+*,:,```.;x@@@@@@n#++zn#+#*###znxxxxMMMMxxxxxxxnnn##znzzz#i;i++*+#zxx#,``         \n" +
                        "               `i#zzzz#zzz#nn##;.i:..```,#xM@@W@@n;:*#+*;*##znxxxxxxMxxxxnnxxnzz##zzzz#*;*#zznxxxz#x+`          \n" +
                        "              `.i###z#+z##zxz#z,,:.;i...```.#Wx@Wx*,;++*:i++#nxxxxxxxxxxnnnnnnz#zzzzzz#i*#z###+i;,.*i           \n" +
                        "              `,i+#####++znzznz;::,++::..`````;@WnM#`;*+*++*+zxnxxxxxxnnnnnnnz##zznnzn*i#z+#*;.,,::;i           \n" +
                        "              `::*##++++znxnzxx*+#*+zz+,``..``.+*WM##::*#*ii+znnxxxxxxxnnnnnzzz#nznnzzi*z#+*:,*zz;,*;  `        \n" +
                        "              .::i++*i**#nxn#nx#Mn+zz+*;...``````znxW;:i+*;i#znnxnnnnnnnnnnnzzzznnnnz#i#z#+#nnMMxi:#,           \n" +
                        "              .::;iiii*+zxxn+#nnWM#+#z#i:.```.` ```#zi:;i*ii#nnnnnnnnnnznnnzzzznnnzzz+izn#;;M@WMn#+x. `         \n" +
                        "              .,:;;;;;*#nxn#++nxx@Wz#zz**;:,````````.ii:i*i*#nnnnnznnnnnnnzzzzznnnnzz+*zx*..zMWMzzxM            \n" +
                        "              .,:;ii;;+zxxnzznMMMWWWMz#zz+++i::..`````*z+*i+nnnxnnzzznnnnnzzzznnnnnzz++zn;.;zxWxnzWi            \n" +
                        "             `.,:;;;:i#xMMMWWW@@WMMW@@Wxxnzxxnz+i,``.;nnz++zxnnnnzzzzznnnnzzzznnnnnzz+#zni,*#xWxnxW   `         \n" +
                        "             `.:;;:::*zMMWMWWWW@WWWMMW@@@@WWWMMxz*;izWxn##znxxxnnnnnnnnnnzzzznnnnznnz*+znzi+#MWxzM+`            \n" +
                        "              ,,::,,;#xMMxxMMWW@@@@@WMMMWWWMMxxxxxMWWMxz+znxxnnnnnnnnznnnnzznnnzznzn#i#zxxn#nMWnn@`             \n" +
                        "             ` :.;+zxMxnnnxxMMW@@@@@WWMMMMMMMMMMMMMxxxxz#zxxxnnnnnnnnznnnzzznnnnznzz+*zzxWMnxWxnW    `          \n" +
                        "        ``  ` .`in@@@WWMnzzzxMWW@@@@@WWxxMMMxxxxxMMxMMnzznxxnnnznnnnnnnnnnzzznnnnnn#+#nxWMMM@MnW, .    `        \n" +
                        "          ``   ;W@@WW@@WWxzznxMW@@@@@@WMnnnnnznnxMMMMMnznnnnnzzznnnnnnnnnnzzzznnnnz+*zxWWMM@WnM:    `           \n" +
                        "        ``    .MWMMW@@@WWWWMxxMW@@@@@@WWnnnnnnxxMMMMMnznnnnzzzznnnnnnxnnzzzzzznnnzz**nMMWxMMzM, `  `            \n" +
                        "             `MWxMW@@@@@W@@@WMMMW@@@@@@WMxxMMMMMMMMMMnznzznzzznnnnnnnnnzzzzzznnnzz#**xMMWMMnM, ` ``  `          \n" +
                        "          `,@@@WWMMMW@@@@WW@@@WMWW@@@@WWWxnxxxxxxxMMxxnzzzzzznnnnnnnnzzzzzzzznnzz#+i*xMMWWMM:`     `` `         \n" +
                        "         `.@@MWWWWWWWWW@@@W@@@@@MMW@@@WWWxnnnxxxMMMxnnn##zzznnnnnnnnnzzzzzzzznzzz#*;izMxMWx,     `     `        \n" +
                        "         `WMMMMMWWWWWWWW@@@@@@@@@WWWWWWWWWnnMxMMMWMxnzz#+zzznnnnnnzzzzzz#zzzznzzz#i,,+xxMM;                     \n" +
                        " ``  ` ` xxxMMMMMWW@@WWWWW@@@@@@@@@@@@@WWWxxMxWMWMxnzz#+###zzznnnnzzzzzz#zzznnnzz+i,:;+##i` `                   \n" +
                        "`   ``  *WnxxMMxMWW@@@@WWW@@@@@@@@@@@@WWWWMMMWWWMn##z##+##zzznnnnnzzz####zzznnnz+*:.,``..`  `  `                \n" +
                        "  `   ``x##nnMMWW@@@@@@@@@@@@@@@@@@@@@@WWWWMWWWMn#*+##+**+znzznnzzzzz####zznnnz#*i,.,`     `                    \n" +
                        "    ` ,WMxnznnWW@@@@@@@@@@@@@@@@W@@@@@@@W@WMWMnnz#*++*iii*#nzzznzzzzz#zzzzznnnz+*;,.:.    `                     \n" +
                        ".`.`.#@WW@WWMxxxWW@@@@@@@@@@@@@@W@@@@@@@W@WWxz#zz+iii;;;;+#+#zzzzzzzzzzznnnnnn#*i.,,i   `                       \n" +
                        ".:`,nWWWMM@@@@MWMW@@@@@@@@@@@@@@@@W@@@@WWWWWn++++*::::;;:++i+z#+zzzzzzzzzznnz#+i;`:ii``        `                \n" +
                        ".`.#xnMxMWWM@@@@WW@@@@@@@@@@@@@@@@W@@@@@@WW@zi#*i;,,,,::i*i*z#*+zz##zzzz#zzz#+*i;::+.     `  `                  \n" +
                        ".`innzxxMMWWW@@@@@@@@@@@@@@@@@@@@@@@@@@@@MW@x*;;,,,:,::;;*+++**#z++zz##++###+i;iz*:z`                           \n" +
                        ",.inznxnxMWWW@@@@@@@@@@@@@@@@@@@@@@@@@@@WWM@W*`.,,.,:;:;i**++**++**++++**#+**;;+n##i   `                        \n" +
                        "..izzznzxxMW@@@@@@@@@@@@@@@@@@@@@@@@@@@@@WM@@z;i,,`,,,,**i+#+**+*i***+*i*+*ii;*zzzn`                            \n" +
                        "..;+++znxxMWW@@@@@@@@@@@@@@@@@@@@@@@@@@@@WWW@WMMMn*.`:iii*#z+**+*i+**+ii+i**:i#zzn*                             \n" +
                        ",.:i;;+nxxMWWWW@@@@W@@@@@@@@@@@@@@@@@@@@WWWWWWWWW@@x;:iii+zz+*+**i+**+i*+iii;*zzz#``                            \n" +
                        "..,:..;+znMWWWWW@@@W@@@@@@@@@@@@@@@@@@@@W@WW@WxMW#nWni*####+*++i*i****;iii;;*#zzz:                              \n" +
                        ".`.,``.,;+nMMWWW@@@@@@@@@@@@@@@@W@@@@@@@WWWW@WMx##nMM#*+##+*++*;**iiii;;;iii#zz#i ` `                           \n" +
                        "...,.````;#nxMWM@@@@@@@@@@@@@@@@W@@@@@@@@WWWM@W##nxxM#*+#+**++i;*+*iiii;;;*+#z#i   `                            \n" +
                        "......```.;#nxMWW@@@@@@@@@@@@@@@@@@WW@@@@@WMWWMn+#+zz**z#*i++i;***ii**i;ii*##+:`                                \n" +
                        "......`` .:i#xxMWW@@@@@@@@@@@@@@@@@WWWWW@@WWWWWM;;;;;*zzz+++*i;i**i*++i;i*++i. `                                \n" +
                        "......``.:;;+zxxMW@@@@@@@@@@@@@@@@@WWWWW@@WWWWWW;``;;#n#+##+*i************i:`                                   \n" +
                        ".....```,ii,;+znMWWW@@@@@@@@@@@@@@@@WWWWWWWWWWWW+`,#zzz+*++****+****iii*;:`  ``                                 \n" +
                        ".....` `:ii,i*zxMMWWWW@@@@@@@@@@@@@@@WWWWWWWWWWWni+nzz#+*****i****iiii:,`   `                                   \n" +
                        "....` `.ii;:+#zxMMWWWWW@@@@@@@@@@@@@@@WW@WWWWWWWx#+###++*****iiiii;:,.`   ```                                   \n" +
                        "...`` .,i;;:*+zzMMWWWWWW@@@@@@@@@@@@@@WWWWWWWWWMM+;i+**iiiiii;;:::,`   ``.`  ```                                \n" +
                        "...`  .;iii:ii#zMMWWWWWWWW@@@@@W@@@@@WWWWWWWWWWMM+:;i;i::;;:::,.``   ``.``    `.                      \"");
            }
        }
        if(randomEvent ==2)
        {
            System.out.println("Do you want to open the chest?");
            Scanner a = new Scanner(System.in);
            String rep = a.nextLine();
            if(rep.equalsIgnoreCase("yes")||rep.equalsIgnoreCase("y"))
            {
                double n = Math.random();
                if(n>.5)
                {
                    System.out.println("You find a bag of gold!");
                    x.gold += 50;
                    System.out.println("You now have " + x.gold + " gold");
                }
                else
                {
                    System.out.println("The chest was trapped! You took 50 damage!");

                    x.hp -= 50;
                    System.out.println("You now have " + x.hp + " health");
                }
                eventClear[2]=true;
            }
            else
            {
                System.out.println("You ignore the chest and move on.");
            }

        }
        if(randomEvent == 3)
        {
            System.out.println("Do you want to drink the potion?");
            Scanner a = new Scanner(System.in);
            String rep = a.nextLine();
            if(rep.equalsIgnoreCase("yes")||rep.equalsIgnoreCase("y"))
            {
                double n = Math.random();
                if(n>.5)
                {
                    System.out.println("You are poisoned!");
                    x.poisoned = true;
                }
                else
                {
                    System.out.println("You feel rejuvenated. You are restored to full hp.");
                    if(x.poisoned)
                    {
                        x.poisoned = false;
                        System.out.println("The potion also cured your poison status");
                    }
                    if(x.amnesia)
                    {
                        x.amnesia = false;
                        System.out.println("The potion cleared your amnesia");
                    }
                    x.hp =100;
                }
                eventClear[3]=true;
            }
            else
            {
                System.out.println("You ignore the potion and move on.");
            }

        }
        if(randomEvent == 5)
        {
            x.gold += 10;
            System.out.println("You now have " + x.gold + "gold.");
        }
        this.cleared = true;
    }

}
