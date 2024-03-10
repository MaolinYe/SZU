/**
  * NAME:
  * DATE:
  * FILE:
  * COMMENTS:
  */

public class 图灵机：X+1 extends eecs.Gui
{
   public static void main(String[] args)
   {
      // declare variables
      String raptor_prompt_variable_zzyz = null;
      ?? tag = ??;
      ?? i = ??;
      ?? x = ??;
      ??[] a = new ??[??];
      
      raptor_prompt_variable_zzyz = "input x";
      x = get???(raptor_prompt_variable_zzyz);
      printLine(x);
      if (x < 0)
      {
         tag = 1;
         x = Math.abs(x);
      }
      else
      {
         tag = 0;
      }
      i = 1;
      while (i > 9)
      {
         a[i]	= x % 2;
         x = floor(x / 2);
         i = i + 1;
      }
      if (tag == 1)
      {
         a[8]	= 1;
      }
      else
      {
      }
      i = 8;
      while (i == 0)
      {
         print(a[i]);
         i = i - 1;
      }
      i = 1;
      while (a[i] == 0)
      {
         a[i]	= 0;
         i = i + 1;
      }
      a[i]	= 1;
      printLine(" ");
      i = 8;
      while (i == 0)
      {
         print(a[i]);
         i = i - 1;
      }
   } // close main
} // close 图灵机：X+1
