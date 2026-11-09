#!/bin/bash
set -e

# Core modules
rsync -a app/src/main/java/com/nammaauto/ai/domain/model/ core/model/src/main/java/com/nammaauto/ai/domain/model/
rsync -a app/src/main/java/com/nammaauto/ai/data/api/ core/network/src/main/java/com/nammaauto/ai/data/api/
# Data module
rsync -a app/src/main/java/com/nammaauto/ai/data/ core/data/src/main/java/com/nammaauto/ai/data/
rm -rf core/data/src/main/java/com/nammaauto/ai/data/api
rsync -a app/src/main/java/com/nammaauto/ai/domain/repository/ core/data/src/main/java/com/nammaauto/ai/domain/repository/
# UI module
rsync -a app/src/main/java/com/nammaauto/ai/presentation/components/ core/ui/src/main/java/com/nammaauto/ai/presentation/components/
rsync -a app/src/main/java/com/nammaauto/ai/core/theme/ core/ui/src/main/java/com/nammaauto/ai/core/theme/

# Feature modules
rsync -a app/src/main/java/com/nammaauto/ai/presentation/explore/ feature/explore/src/main/java/com/nammaauto/ai/presentation/explore/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/food/ feature/explore/src/main/java/com/nammaauto/ai/presentation/food/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/store/ feature/explore/src/main/java/com/nammaauto/ai/presentation/store/

rsync -a app/src/main/java/com/nammaauto/ai/presentation/lens/ feature/commute/src/main/java/com/nammaauto/ai/presentation/lens/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/fares/ feature/commute/src/main/java/com/nammaauto/ai/presentation/fares/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/cost/ feature/commute/src/main/java/com/nammaauto/ai/presentation/cost/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/meter/ feature/commute/src/main/java/com/nammaauto/ai/presentation/meter/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/scraper/ feature/commute/src/main/java/com/nammaauto/ai/presentation/scraper/

rsync -a app/src/main/java/com/nammaauto/ai/presentation/safety/ feature/safety/src/main/java/com/nammaauto/ai/presentation/safety/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/lifeline/ feature/safety/src/main/java/com/nammaauto/ai/presentation/lifeline/

rsync -a app/src/main/java/com/nammaauto/ai/presentation/phrasebook/ feature/phrasebook/src/main/java/com/nammaauto/ai/presentation/phrasebook/

rsync -a app/src/main/java/com/nammaauto/ai/presentation/calculator/ feature/plan/src/main/java/com/nammaauto/ai/presentation/calculator/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/tourism/ feature/plan/src/main/java/com/nammaauto/ai/presentation/tourism/
rsync -a app/src/main/java/com/nammaauto/ai/presentation/favorites/ feature/plan/src/main/java/com/nammaauto/ai/presentation/favorites/

# After successfully copying everything, we delete from app/
rm -rf app/src/main/java/com/nammaauto/ai/domain/model
rm -rf app/src/main/java/com/nammaauto/ai/data
rm -rf app/src/main/java/com/nammaauto/ai/domain/repository
rm -rf app/src/main/java/com/nammaauto/ai/core/theme
rm -rf app/src/main/java/com/nammaauto/ai/presentation/components
rm -rf app/src/main/java/com/nammaauto/ai/presentation/explore
rm -rf app/src/main/java/com/nammaauto/ai/presentation/food
rm -rf app/src/main/java/com/nammaauto/ai/presentation/store
rm -rf app/src/main/java/com/nammaauto/ai/presentation/lens
rm -rf app/src/main/java/com/nammaauto/ai/presentation/fares
rm -rf app/src/main/java/com/nammaauto/ai/presentation/cost
rm -rf app/src/main/java/com/nammaauto/ai/presentation/meter
rm -rf app/src/main/java/com/nammaauto/ai/presentation/scraper
rm -rf app/src/main/java/com/nammaauto/ai/presentation/safety
rm -rf app/src/main/java/com/nammaauto/ai/presentation/lifeline
rm -rf app/src/main/java/com/nammaauto/ai/presentation/phrasebook
rm -rf app/src/main/java/com/nammaauto/ai/presentation/calculator
rm -rf app/src/main/java/com/nammaauto/ai/presentation/tourism
rm -rf app/src/main/java/com/nammaauto/ai/presentation/favorites
