# reveal-progress-image-plugin
Add an image in your reveal.js progress bar

## Use plugin

````js
<script type="module">
    import RevealProgressImage from "../node_modules/@cfrezier/reveal-progress-image-plugin/dist/index.js";
    
    Reveal.initialize({
        hash: true,
        progress: true,
        plugins: [RevealMarkdown, RevealHighlight, RevealNotes, RevealProgressImage(Reveal)],
        });
</script>
````

This is available inside an ESModule, so you probably should use an `<script type="module">` tag.

## Custom Configuration

### Overload configuration

Please make sure to include a file at ./images/progress.web or change the parameter. 
You can overload this configuration like this:
````js
Reveal.initialize({
    hash: true,
    progress: true,

    progressImage: {
        imageSrc: './my-image.png'
    },
    
    plugins: [RevealMarkdown, RevealHighlight, RevealNotes, RevealProgressImage(Reveal)],
});
````


### Default configuration

By defaut configuration is set to:
````js
    iconSize: [30, 30],
    bottom: 5,
    moveTransition: 'left .8s cubic-bezier(.26,.86,.44,.985)',
    imageSrc: './images/progress.webp',
    imageAnimation: ANIMATIONS.DANCE,
````

### Use preconfigured animations
````js
const progressPlugin = RevealProgressImage(Reveal);

Reveal.initialize({
    hash: true,
    progress: true,
    progressImage: {
        imageAnimation: progressPlugin.ANIMATIONS.FLY
    },
    plugins: [RevealMarkdown, RevealHighlight, RevealNotes, progressPlugin],
});
````
