# FractalRayMarcher
A ray marcher for rendering 3D fractals.

### Some renders

![Alt text](/FractalMarcher/Renders/renderGif.gif?raw=true "Mandelbulb changing as power increases")

### What is it?
This is a ray marching renderer for visualizing 3D fractals. These fractals are defined by Signed Distance Functions, which provide the basis on which ray marching is performed. 

It is able to produce stunning images and animations.

### What features does it have?
- rendered fractals have an 'outer glow' effect as well as an 'internal glow' to make them look cooler
- good looking colour scheme (colour based on distance point is from the origin)
- multithreading
- uses only CPU

### What did I learn from it?
- 3D maths (vectors, rays)
- Using multithreading in Java to parallelize rendering
- Basics of ray tracing too (I ended up not using it in this project, though)
  - Diffuse lighting, specular highlights
  - Reflections and conservation of light energy
  - Tracing shadow rays and faking soft shadows
  - Coloured and multiple lights
- I also learned how to create a path tracer (again, not used in this particular project)
  - Rendering equation
  - Explicit light sampling
  - Texture mapping, normal maps and specular maps

### Info.
I made this in the beginning of Grade 12.

Visit my Youtube for more projects and more to come in the future
https://www.youtube.com/user/Antfs10/
