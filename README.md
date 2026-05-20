<img width="1600" height="900" alt="Lab 6" src="https://github.com/user-attachments/assets/14084cce-dbf8-4906-b581-f8aa4651034d" />
# Lab 6 - Blending

## Ce face aplicatia
Demonstreaza alpha blending in OpenGL ES 1.x.
Pe ecran apar:
- un patrat albastru transparent care se misca sus-jos
- un patrat rosu transparent care se misca stanga-dreapta
- un patrat texturat transparent
Cand patratele se suprapun, culorile se amesteca.
## Cerinte implementate
- pornire de la BouncySquare
- fundal negru cu `glClearColor(0, 0, 0, 1)`
- `glEnable(GL_BLEND)`
- `glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)`
- `glColor4f` cu alpha
- doua patrate suprapuse
- miscare verticala/orizontala
- exemplu de blending cu textura
- textura `hedly.png` in `res/drawable`
- comentarii pentru experimente cu `GL_ONE`, `GL_ONE` si `glColorMask`

