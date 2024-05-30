export const ANIMATIONS = {
    FLIP: [
        { transform: 'rotateY(0deg)' },
        { transform: 'rotateY(360deg)' }
    ],
    FLY: [
        { transform: 'translateX(0px)' },
        { transform: 'translateX(5px)' },
        { transform: 'translateY(5px)' },
        { transform: 'translateX(0px)' },
    ],
    DANCE: [
        { transform: 'rotate(-20deg)' },
        { transform: 'rotate(20deg)' },
        { transform: 'rotate(-20deg)' },
    ],
    MOVE: [
        { transform: 'translateX(0px)' },
        { transform: 'translateX(5px)' },
        { transform: 'translateX(0px)' },
    ],
    CIRCULAR_FLY: [
        { transform: 'translate(0px, 0px)' },
        { transform: 'translate(2.5px, -2.5px)' },
        { transform: 'translate(0px, -5px)' },
        { transform: 'translate(-2.5px, -2.5px)' },
        { transform: 'translate(0px, 0px)' },
    ]
};
const RevealProgressImage = ((Reveal) => {
    return {
        id: 'progressImage',
        ANIMATIONS,
        init: (reveal) => {
            let config = Object.assign({ iconSize: [30, 30], bottom: 5, moveTransition: 'left .8s cubic-bezier(.26,.86,.44,.985)', imageSrc: './images/progress.webp', imageAnimation: ANIMATIONS.DANCE, animationDuration: 1000 }, reveal.getConfig().progressImage);
            const progressImage = document.createElement('img');
            progressImage.style.position = 'absolute';
            progressImage.style.maxWidth = config.iconSize[0] + 'px';
            progressImage.style.maxHeight = config.iconSize[1] + 'px';
            progressImage.style.bottom = config.bottom + 'px';
            progressImage.style.transition = config.moveTransition;
            progressImage.src = config.imageSrc;
            if (config.imageAnimation) {
                progressImage.animate(config.imageAnimation, {
                    duration: config.animationDuration,
                    iterations: Infinity
                });
            }
            document.body.appendChild(progressImage);
            const updatePosition = () => {
                const progress = Reveal.getProgress();
                progressImage.style.left = `calc(${progress * 100}% - ${config.iconSize[0] * progress}px)`;
            };
            Reveal.on('ready', updatePosition);
            Reveal.on('resize', updatePosition);
            Reveal.on('slidechanged', updatePosition);
        }
    };
});
export default RevealProgressImage;
